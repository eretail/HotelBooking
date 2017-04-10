package com.eretailservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.eretailservice.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
