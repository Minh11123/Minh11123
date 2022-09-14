package com.vti.rk25finalexam.controller;

import com.vti.rk25finalexam.entity.Account;
import com.vti.rk25finalexam.entity.criteria.AccountCriteria;
import com.vti.rk25finalexam.entity.dto.AccountCreateDTO;
import com.vti.rk25finalexam.entity.dto.AccountDTO;
import com.vti.rk25finalexam.entity.dto.AccountUpdateDTO;
import com.vti.rk25finalexam.service.AccountService;
import com.vti.rk25finalexam.spec.Expression;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public ResponseEntity<Page<AccountDTO>> getAll(
        AccountCriteria criteria,
        Pageable pageable
    ) {
        Page<AccountDTO> accountList = accountService.findAllByCriteria(criteria, pageable);
        return ResponseEntity
            .ok()
            .body(accountList);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<AccountDTO>> getOne(@PathVariable Integer id) {
//        Optional<AccountDTO> accountDTO =
//            accountService.getOneReturnDTO(id).map(accDto -> {
//                accDto.add(
//                    WebMvcLinkBuilder.linkTo(
//                        WebMvcLinkBuilder.methodOn(AccountController.class).getOne(id))
//                    .withSelfRel());
//                accDto.add(
//                    WebMvcLinkBuilder.linkTo(
//                        WebMvcLinkBuilder
//                            .methodOn(DepartmentController.class)
//                            .getOne(accDto.getDepartmentId()))
//                        .withSelfRel());
//                return accDto;
//            });
//        return ResponseEntity
//            .ok()
//            .body(accountDTO);
//    }

    @PostMapping
    public ResponseEntity<AccountDTO> create(
            @RequestBody @Validated AccountCreateDTO accountCreateDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(accountService.create(accountCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> update(
        @PathVariable Integer id,
        @RequestBody AccountUpdateDTO accountUpdateDTO
    ) throws NotFoundException {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(accountService.update(id, accountUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Account> delete(@PathVariable Integer id)
        throws NotFoundException {
        Account responseAccount = accountService.delete(id);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(responseAccount);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<AccountDTO>> getByUsername(@PathVariable String username) {
        return ResponseEntity
            .ok()
            .body(accountService.findByUsernameEquals(username));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AccountDTO>> filter(Expression expression) {

        try {
            return ResponseEntity
                .ok()
                .body(accountService.getAll(expression));
        } catch (Exception e) {
            return ResponseEntity
                .ok()
                .body(new ArrayList<>());
        }
    }

}
