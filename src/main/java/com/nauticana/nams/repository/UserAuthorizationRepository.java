package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.UserAuthorization;
import com.nauticana.nams.model.UserAuthorizationId;

public interface UserAuthorizationRepository extends JpaRepository<UserAuthorization, UserAuthorizationId> {

}
