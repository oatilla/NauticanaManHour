package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.DomainValue;
import com.nauticana.manhour.model.DomainValueId;

public interface DomainValueRepository extends JpaRepository<DomainValue, DomainValueId>{

}
