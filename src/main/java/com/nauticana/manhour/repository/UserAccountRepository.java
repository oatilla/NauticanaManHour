package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
