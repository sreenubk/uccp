package com.lotus.uccp.consentmgmt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lotus.uccp.util.PersonSearchUtil;
import com.lotus.uccp.wcm.vo.PersonSearchVo;

@RestController
@RequestMapping("/person")
public class PersonFinder {

	public static final Logger logger = LoggerFactory.getLogger(PersonFinder.class);

	/*---------------Services for MDM Person Search - START------------------*/

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/query1", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> searchForIdList(@RequestBody PersonSearchVo personSearchVo) {

//		System.out.println(":: start searchForIdList ##::");
//
//		/*------------- INPUTS - START--------------*/
//		System.out.println(":: Input First Name ::" + personSearchVo.getFirstName());
//		System.out.println(":: Input Middle Name ::" + personSearchVo.getMiddleName());
//		System.out.println(":: Input Last Name ::" + personSearchVo.getLastName());
//		System.out.println(":: Input SSN ::" + personSearchVo.getSsn());
//		System.out.println(":: Input Address ::" + personSearchVo.getAddress());
//		System.out.println(":: Input City ::" + personSearchVo.getCity());
//		System.out.println(":: Input State::" + personSearchVo.getState());
//		System.out.println(":: Input Zip ::" + personSearchVo.getZipCode());
//		System.out.println(":: Input Date of Birth ::" + personSearchVo.getDateofBirth());
//		System.out.println(":: Input Phone ::" + personSearchVo.getPhone());
//		System.out.println(":: Input Gender ::" + personSearchVo.getGender());
//
//		List<IdEntitySearchResult> idEntitySearchResults = null;
//		List<IdEntitySearchResult> idEntitySearchNewResults = new ArrayList<IdEntitySearchResult>();
//
//		try {
//
//			String sURL = "http://localhost:8983/solr/customerdb/query?q=" + personSearchVo.getFirstName();
//			URL url = new URL(sURL);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setRequestProperty("Accept", "application/json");
//
//			if (conn.getResponseCode() != 200) {
//				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
//			}
//
//			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
//
//			String output;
//			System.out.println("Output from Server .... \n");
//			while ((output = br.readLine()) != null) {
//				System.out.println(output);
//			}
//
//			conn.disconnect();
//
//		} catch (MalformedURLException e) {
//
//			e.printStackTrace();
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//
//		}
//
//		idEntitySearchNewResults = PersonSearchUtil.getFilteredData(idEntitySearchNewResults);

		return new ResponseEntity<String>("Hello Person1", HttpStatus.OK);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> searchCustomerByKeword(@RequestBody PersonSearchVo personSearchVo) {

		System.out.println(":: start searchForIdList ##::");

		/*------------- INPUTS - START--------------*/
		System.out.println(":: Input First Name ::" + personSearchVo.getFirstName());
		System.out.println(":: Input Middle Name ::" + personSearchVo.getMiddleName());
		System.out.println(":: Input Last Name ::" + personSearchVo.getLastName());
		System.out.println(":: Input SSN ::" + personSearchVo.getSsn());
		System.out.println(":: Input Address ::" + personSearchVo.getAddress());
		System.out.println(":: Input City ::" + personSearchVo.getCity());
		System.out.println(":: Input State::" + personSearchVo.getState());
		System.out.println(":: Input Zip ::" + personSearchVo.getZipCode());
		System.out.println(":: Input Date of Birth ::" + personSearchVo.getDateofBirth());
		System.out.println(":: Input Phone ::" + personSearchVo.getPhone());
		System.out.println(":: Input Gender ::" + personSearchVo.getGender());

		final String uri = "http://localhost:8983/solr/customerdb/query?q=" + personSearchVo.getFirstName();

		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		System.out.println(result);

		return result;

	}

	/*---------------Services for ReferralPersonLA - End------------------*/
}
