package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
