package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.PageAuthorization;
import com.gama.manhour.model.PageAuthorizationId;

public interface PageAuthorizationRepository extends JpaRepository<PageAuthorization, PageAuthorizationId> {

}
