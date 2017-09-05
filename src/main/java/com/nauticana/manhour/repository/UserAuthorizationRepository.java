package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.model.UserAuthorizationId;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, UserAuthorizationId> {

}
