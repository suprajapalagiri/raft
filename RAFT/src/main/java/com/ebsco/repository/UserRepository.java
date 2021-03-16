package com.ebsco.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ebsco.entity.UserDetails;

public interface UserRepository extends JpaRepository<UserDetails, Integer> {

	public Optional<UserDetails> findByempId(String userName);

	@Query(value = "select * from userdetails where empId=?1 and  BINARY password like ?2", nativeQuery = true)
	public Optional<UserDetails> findByUserNameAndPassword(String userName, String password);

	@Query(value = "select costcenter from userdetails where empid=?1", nativeQuery = true)
	public String findCostCenterByPMId(String empId);

	@Query(value = "select * from userdetails limit ?1 , ?2", nativeQuery = true)
	public List<UserDetails> findAll(Integer pageSize, Integer pageNumber);

	@Query(value = "select count(*) from userdetails", nativeQuery = true)
	public int getAllUsersCount();
}
