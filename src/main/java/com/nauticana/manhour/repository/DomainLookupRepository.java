package com.nauticana.manhour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.manhour.model.DomainLookup;
import com.nauticana.manhour.model.DomainLookupId;

public interface DomainLookupRepository extends JpaRepository<DomainLookup, DomainLookupId> {
}
