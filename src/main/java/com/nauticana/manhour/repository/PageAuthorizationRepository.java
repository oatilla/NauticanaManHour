package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.PageAuthorization;
import com.nauticana.manhour.model.PageAuthorizationId;

public interface PageAuthorizationRepository extends JpaRepository<PageAuthorization, PageAuthorizationId> {

}
