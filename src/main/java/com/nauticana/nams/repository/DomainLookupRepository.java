package com.nauticana.nams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nauticana.nams.model.DomainLookup;
import com.nauticana.nams.model.DomainLookupId;

public interface DomainLookupRepository extends JpaRepository<DomainLookup, DomainLookupId> {
}
