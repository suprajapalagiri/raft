package com.ebsco.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ebsco.constants.EbescoConstants.*;
import com.ebsco.entity.FullTextRequest;
import com.ebsco.http.HttpRequestor;
import com.ebsco.repository.FullTextRepository;
import com.ebsco.repository.UserRepository;
import com.ebsco.service.FullTextService;
import com.ebsco.util.HtmlReader;

@Service
public class FullTextServiceImpl implements FullTextService {

	@Autowired
	FullTextRepository reposistory;

	@Autowired
	UserRepository userRepostiry;

	@Override
	public FullTextRequest findByPmId(Integer id, String userName) {
		FullTextRequest fullTextRequest = null;
		boolean flag = false;
		fullTextRequest = reposistory.findByPmId(id);
		if (Objects.nonNull(fullTextRequest)) {
			return fullTextRequest;
		}
		if (!flag) {

			int requestId = reposistory.findMaxRequestId();

			try {
				System.out.println("PubMed");

				String sendingPostRequest = HttpRequestor.getResponse(NCBI + id + "/");

				FullTextRequest fRequest = HtmlReader.processHtmlData(sendingPostRequest, requestId);

				FullTextRequest save = reposistory.save(fRequest);
				System.out.println("The saved entity" + save);
				flag = true;
				return save;

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			try {
				System.out.println("ebesco");
				HttpRequestor.sendingPostRequest(EBESCOHOSTPREFIX + id + EBESCOHOSTSUFFIX);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return null;
		}

	}
}
