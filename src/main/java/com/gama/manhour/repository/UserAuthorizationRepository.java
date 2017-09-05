package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.UserAuthorization;
import com.gama.manhour.model.UserAuthorizationId;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, UserAuthorizationId> {

}
