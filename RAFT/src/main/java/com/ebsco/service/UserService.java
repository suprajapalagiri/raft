package com.ebsco.service;

import java.util.Optional;

import com.ebsco.dto.Response;
import com.ebsco.dto.SearchTemplate;
import com.ebsco.entity.UserDetails;

public interface UserService {
	public UserDetails save(UserDetails user);

	public Optional<UserDetails> get(String userName);

	public UserDetails findByUserNameAndPassword(String userName, String password);
	
	public Response findAll(SearchTemplate detailsSearch);

}
