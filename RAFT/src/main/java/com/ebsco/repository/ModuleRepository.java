package com.ebsco.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ebsco.entity.Module;

/**
 * @author phanikumar
 *
 */
@Repository
public interface ModuleRepository extends JpaRepository<Module, Integer> {

	/**
	 * @param fromPageSize  :this is the starting row number which we are getting
	 *                      from the Database eg:if fromPageSize=10 and
	 *                      fromPageSize=5 then it will take the records from 10 to
	 *                      the 15 becuase the pageSizeLimit is 5
	 * @param pageSizeLimit :the limit of pageSize which we need to get per page
	 * @return
	 */
	@Query(value = "select * from module limit ?1 ,?2", nativeQuery = true)
	public List<Module> getAllModules(@NotNull Integer fromPageSize, @NotNull Integer pageSizeLimit);

	
	@Query(value = "select count(*) from module", nativeQuery = true)
	int getmoduleCount();
}
