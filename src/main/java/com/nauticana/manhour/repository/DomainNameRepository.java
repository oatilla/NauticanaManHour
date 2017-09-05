package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.DomainName;

public interface DomainNameRepository extends JpaRepository<DomainName, String>{

}
