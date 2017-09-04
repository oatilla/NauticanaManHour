package com.gama.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gama.manhour.model.DomainValue;
import com.gama.manhour.model.DomainValueId;

public interface DomainValueRepository extends JpaRepository<DomainValue, DomainValueId>{

}
