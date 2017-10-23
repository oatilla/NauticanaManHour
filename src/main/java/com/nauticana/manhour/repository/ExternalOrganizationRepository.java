package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.ExternalOrganization;

public interface ExternalOrganizationRepository extends JpaRepository<ExternalOrganization, Integer> {

}
