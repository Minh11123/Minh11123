package com.vti.rk25finalexam.service;

import com.vti.rk25finalexam.common.Constants;
import com.vti.rk25finalexam.common.Constants.ACCOUNT;
import com.vti.rk25finalexam.common.Constants.IS_DELETED;
import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.dto.AccountCreateDTO;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.AccountUpdateDTO;
import com.vti.rk25finalexam.exception.RK25Exception;
import com.vti.rk25finalexam.exception.Rk25Error;
import com.vti.rk25finalexam.repository.AccountRepository;
import com.vti.rk25finalexam.spec.AccountSpec;
import com.vti.rk25finalexam.spec.Expression;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vti.rk25finalexam.spec.filter.IntegerFilter;
import com.vti.rk25finalexam.utils.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final QueryService<Account> queryService;
    private final DepartmentService departmentService;

    public AccountServiceImpl(AccountRepository accountRepository,
                              ModelMapper modelMapper,
                              QueryService<Account> queryService,
                              DepartmentService departmentService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.queryService = queryService;
        this.departmentService = departmentService;
    }

    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getOne(Integer id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<AccountDTO> getOneReturnDTO(Integer id) {
        return getOne(id).map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account create(Account account) {
        return save(account);
    }

    @Override
    public AccountDTO update(
            Integer id,
            AccountUpdateDTO accountUpdateDTO
    ) {
        // check id của account có tồn tại hay không?
        // nếu có -> update dữ liệu của account thành các dữ liệu mới trong accountUpdateDTO
        //           nếu departmentId == null -> set department của account về null
        //           nếu departmentId != null -> update department mới cho account
        // nếu không có account ->  return null;

        return getOne(id)
                .map(account -> modelMapper.map(accountUpdateDTO, Account.class))
                .map(account -> {
                    Optional.ofNullable(accountUpdateDTO.getDepartmentId())
                            .flatMap(departmentdId -> departmentService.getOne(departmentdId)
                                    .map(department -> {
                                        account.department(department);
                                        return account;
                                    })).orElseGet(() -> account.department(null));
                    return account;
                })
                .map(this::save)
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .orElse(null);
    }

    @Override
    public Account delete(Integer id) throws NotFoundException {
        return getOne(id)
                .map(account -> {
                    account.id(id);
                    account.isDeleted(IS_DELETED.TRUE);
                    accountRepository.save(account);
                    return account;
                })
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public Page<AccountDTO> getAllReturnDTO(Pageable pageable) {

        Page<Account> page = accountRepository
                .findAll(pageable);

        List<AccountDTO> accountDtoList = page.getContent()
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(accountDtoList, pageable, page.getTotalElements());
    }

    @Override
    public List<AccountDTO> findByUsernameContains(String username) {
        return accountRepository.findAllByUsernameContains(username)
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDTO> timTheoFirstnameLastname(String firstname, String lastname) {
        return accountRepository.timTheoFirstnameLastname(firstname, lastname)
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> findByUsernameEquals(String username) {

        return accountRepository.findByUsername(username)
                .map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public Page<AccountDTO> findAllByCriteria(
            AccountCriteria criteria,
            Pageable pageable) {

        Specification<Account> spec = buildWhere(criteria);

        Page<Account> page = accountRepository.findAll(spec, pageable);

        List<AccountDTO> accountDtoList = page.getContent()
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(accountDtoList, pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public AccountDTO create(AccountCreateDTO accountCreateDTO) {
        validateCreate(accountCreateDTO);
        return departmentService.getOne(accountCreateDTO.getDepartmentId())
                .map(department -> {
                    Account account =
                            modelMapper.map(accountCreateDTO, Account.class)
                                    .id(null)
                                    .department(department);
                    return create(account);
                }).map(account -> modelMapper.map(account, AccountDTO.class))
                .orElse(null);
    }

    private void validateCreate(AccountCreateDTO accountCreateDTO) {
        validateCreateUsername(accountCreateDTO.getUsername());
        validateRole(accountCreateDTO.getRole());
        validateDepartment(accountCreateDTO.getDepartmentId());
    }

    private void validateDepartment(Integer departmentId) {
        Optional.ofNullable(departmentId)
                .map(deptId -> {
                    departmentService
                            .getOne(departmentId)
                            .orElseThrow(() -> new RK25Exception()
                                            .rk25Error(new Rk25Error()
                                                    .code("account.departmentId.isNotExisted")
                                                    .param(departmentId)));
                    return deptId;
                }).orElseThrow(() -> new RK25Exception()
                                        .rk25Error(new Rk25Error()
                                                .code("account.departmentId.isNull")
                                                .param(departmentId)));
    }

    private void validateRole(String role) {
        if (Constants.ROLE.ADMIN.equals(role) ||
                Constants.ROLE.EMPLOYEE.equals(role) ||
                Constants.ROLE.MANAGER.equals(role)) {
            return;
        }
        throw new RK25Exception()
                    .rk25Error(new Rk25Error()
                            .code("account.role.isNotValid")
                            .param(role));
    }

    private void validateCreateUsername(String username) {
        // check username có tồn tại trong hệ thông
        findByUsername(username)
                .map(account -> {
                    throw new RK25Exception()
                        .rk25Error(new Rk25Error()
                                .code("account.username.usernameIsNotExists")
                                .param(username));
                });

        // check username không được chứa khoảng trắng
        // TODO
    }

    private Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<AccountDTO> getAll(Expression expression) throws Exception {

        validateExpression(expression);

        AccountSpec accountSpec = new AccountSpec(expression);
        Specification<Account> where = Specification.where(accountSpec);

        return accountRepository.findAll(where)
                .stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .collect(Collectors.toList());

    }

    private void validateExpression(Expression expression) throws Exception {
        validateField(expression.getField());
        validateOperator(expression.getOperator());
        validateValue(expression.getValue());
    }

    private void validateValue(Object value) throws Exception {
        if (value == null || value.equals("")) {
            throw new Exception("value is not null");
        }
    }

    private void validateOperator(String operator) throws Exception {
        if (operator == null || operator.equals("")) {
            throw new Exception("operator is not null");
        }
    }

    private void validateField(String field) throws Exception {
        if (field == null || field.equals("")) {
            throw new Exception("field is not null");
        }
    }

    private Specification<Account> buildWhere(AccountCriteria criteria) {
        Specification<Account> spec = Specification.where(null);

        if (criteria.getId() != null) {
            spec = spec.and(queryService.buildIntegerFilter(ACCOUNT.ID, criteria.getId()));
        }
        if (criteria.getUsername() != null) {
            spec = spec.and(queryService.buildStringFilter(ACCOUNT.USERNAME, criteria.getUsername()));
        }
        if (criteria.getFirstName() != null) {
            spec = spec.and(queryService.buildStringFilter(ACCOUNT.FIRST_NAME, criteria.getFirstName()));
        }
        if (criteria.getLastName() != null) {
            spec = spec.and(queryService.buildStringFilter(ACCOUNT.LAST_NAME, criteria.getLastName()));
        }
        if (criteria.getRole() != null) {
            spec = spec.and(queryService.buildStringFilter(ACCOUNT.ROLE, criteria.getRole()));
        }
        if (criteria.getSearch() != null) {
            Specification<Account> orSpec = Specification.where(null);
            orSpec = orSpec
                    .or(queryService.buildStringFilter(ACCOUNT.USERNAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(ACCOUNT.FIRST_NAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(ACCOUNT.LAST_NAME, criteria.getSearch()))
                    .or(queryService.buildStringFilter(ACCOUNT.ROLE, criteria.getSearch()));

            if (Utils.checkStringAsDigit(criteria.getSearch().getContains())) {
                Integer searchValue = Integer.valueOf(criteria.getSearch().getContains());
                IntegerFilter integerFilter = new IntegerFilter();
                integerFilter.setEquals(searchValue);
                orSpec.or(queryService.buildIntegerFilter(ACCOUNT.ID, integerFilter));
            }
            spec = spec.and(orSpec);
        }
        return spec;
    }
}
