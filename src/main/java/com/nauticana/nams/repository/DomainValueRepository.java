package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.DomainValue;
import com.nauticana.nams.model.DomainValueId;

public interface DomainValueRepository extends JpaRepository<DomainValue, DomainValueId>{

}
