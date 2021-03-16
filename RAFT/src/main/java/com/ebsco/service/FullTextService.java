package com.ebsco.service;

import com.ebsco.entity.FullTextRequest;

public interface FullTextService {

	FullTextRequest findByPmId(Integer id,String userName);

}
