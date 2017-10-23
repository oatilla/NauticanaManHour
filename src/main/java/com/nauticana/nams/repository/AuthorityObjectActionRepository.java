package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.AuthorityObjectAction;
import com.nauticana.nams.model.AuthorityObjectActionId;

public interface AuthorityObjectActionRepository extends JpaRepository<AuthorityObjectAction, AuthorityObjectActionId>{

}
