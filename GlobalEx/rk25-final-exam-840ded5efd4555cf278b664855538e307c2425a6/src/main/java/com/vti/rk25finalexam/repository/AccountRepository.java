package com.vti.rk25finalexam.repository;

import com.vti.rk25finalexam.entity.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends
    JpaRepository<Account, Integer>,
    JpaSpecificationExecutor<Account> {

    List<Account> findAllByUsernameContains(String username);

    Optional<Account> findByUsername(String username);

    @Query("select a from Account a where a.firstName = ?1 and a.lastName = ?2")
    List<Account> timTheoFirstnameLastname(String firstname, String lastname);
}
