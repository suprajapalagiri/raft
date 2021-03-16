package com.ebsco.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class HttpRequestor {

	// HTTP Post request
	public static String sendingPostRequest(String url) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// Setting basic post request
		con.setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("nSending 'POST' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();

		// printing result from response
		System.out.println(response.toString());
		return response.toString();
	}
	
	
	
	public static String getResponse(String URL) throws IOException{

	    try{
	        String response_string=null;
	        StringBuilder response  = new StringBuilder();
	        URL url = new URL(URL);
	        HttpURLConnection httpconn = (HttpURLConnection) url.openConnection();

	        if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK){
	            BufferedReader input = new BufferedReader(new InputStreamReader(httpconn.getInputStream()));
	            String strLine = null;
	            while ((strLine = input.readLine()) != null){
	                response.append(strLine);
	            }
	            input.close();
	            response_string = response.toString();
	        }

	        httpconn.disconnect();

	        return response_string;
	    }
	    catch(Exception e){
	        throw new IOException();
	    }

	}
	
	public static void main(String[] args) throws IOException {
		String response = getResponse("https://www.ing.nl/api/locator/atms/");
		String mainResponse=response.substring(5, response.length());
		Files.write(Paths.get("./input.json"),mainResponse.getBytes(),StandardOpenOption.CREATE);
	}
	

}
