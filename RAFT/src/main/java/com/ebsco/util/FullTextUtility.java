package com.ebsco.util;

import static com.ebsco.constants.EbescoConstants.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ebsco.http.HttpRequestor;

public class FullTextUtility {

	public static List<Integer> processIds(String ids) {

		String[] split = ids.split(COMMASEPERATOR);

		if (split.length > 0) {
			return convertfromArraytoList(split);
		} else {
			return Arrays.asList(Integer.parseInt(ids));
		}

	}

	private static List<Integer> convertfromArraytoList(String[] array) {
		return Stream.of(array).map(Integer::parseInt).collect(Collectors.toList());

	}

	public static void processEbescoUrl(Integer id) {

		try {
			System.out.println(HttpRequestor.sendingPostRequest(EBESCOHOSTPREFIX + id + EBESCOHOSTSUFFIX));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
