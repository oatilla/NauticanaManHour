package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.UserAuthorityLimit;
import com.nauticana.manhour.model.UserAuthorityLimitId;

public interface UserAuthorityLimitRepository extends JpaRepository<UserAuthorityLimit, UserAuthorityLimitId> {

}
