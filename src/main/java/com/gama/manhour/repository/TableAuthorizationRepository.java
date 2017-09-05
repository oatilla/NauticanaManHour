package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.TableAuthorization;
import com.gama.manhour.model.TableAuthorizationId;

public interface TableAuthorizationRepository extends JpaRepository<TableAuthorization, TableAuthorizationId> {

}
