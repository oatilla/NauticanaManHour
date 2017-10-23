package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.ObjectAuthorization;
import com.nauticana.nams.model.ObjectAuthorizationId;

public interface ObjectAuthorizationRepository extends JpaRepository<ObjectAuthorization, ObjectAuthorizationId> {

}
