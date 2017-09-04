package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.DomainName;

public interface DomainNameRepository extends JpaRepository<DomainName, String>{

}
