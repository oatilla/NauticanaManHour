package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.TableAuthorization;
import com.nauticana.manhour.model.TableAuthorizationId;

public interface TableAuthorizationRepository extends JpaRepository<TableAuthorization, TableAuthorizationId> {

}
