package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

}
