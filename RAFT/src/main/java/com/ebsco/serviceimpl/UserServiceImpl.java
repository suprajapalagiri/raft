package com.ebsco.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebsco.dto.ExceptionMessage;
import com.ebsco.dto.Response;
import com.ebsco.dto.SearchTemplate;
import com.ebsco.entity.UserDetails;
import com.ebsco.repository.UserRepository;
import com.ebsco.service.UserService;
import com.ebsco.util.PaginationUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repository;

	public UserDetails save(UserDetails user) {

		return repository.save(user);

	}

	public Optional<UserDetails> get(String userName) {
		return repository.findByempId(userName);
	}

	@Override
	public UserDetails findByUserNameAndPassword(String userName, String password) {
		UserDetails users = repository.findByUserNameAndPassword(userName, password).orElse(null);
		if (users != null)
			return users;
		else
			return null;

	}

	@Override
	public Response findAll(SearchTemplate detailsSearch) {
		Integer pageSize = PaginationUtils.getPageSize(detailsSearch.getPageSize(), detailsSearch.getPageNumber());
		List<UserDetails> userDetailsList = repository.findAll(pageSize, detailsSearch.getPageSize());
		int totalRecords = repository.getAllUsersCount();
		int totalPages = PaginationUtils.getTotalPages(totalRecords, detailsSearch.getPageSize());
		Response response = new Response(userDetailsList, ExceptionMessage.OK);
		response.setTotalPages(totalPages);
		response.setTotalRecords(totalRecords);

		return response;
	}

}
