package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.DomainName;

public interface DomainNameRepository extends JpaRepository<DomainName, String>{

}
