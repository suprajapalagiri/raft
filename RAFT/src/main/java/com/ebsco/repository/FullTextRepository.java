package com.ebsco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebsco.entity.FullTextRequest;

public interface FullTextRepository extends JpaRepository<FullTextRequest, Integer> {

	@Query(value = "select * from  fulltextrequestinfo  where pmid=?1", nativeQuery = true)
	FullTextRequest findByPmId(Integer pmid);

	@Query(value = "select max(RequestID) from fulltextrequestinfo", nativeQuery = true)
	int findMaxRequestId();
}
