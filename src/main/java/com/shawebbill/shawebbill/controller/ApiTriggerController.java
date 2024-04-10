package com.shawebbill.shawebbill.controller;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@RequestMapping(value="/sha")
public class ApiTriggerController {
    @Autowired
    private RestTemplate restTemplate;
	@RequestMapping(value="/programs",method=RequestMethod.GET)
	public ResponseEntity getPrograms() throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();
 		String username = "APIUser";
		String password = "MTE5MF4qKl55NTN4dWVmc3k1M3h1ZWZz";
		String credentials = username + ":" + password;
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://sandbox.pha-web.com/api/vendor/v1/program"))
				.header("Authorization", "Basic " + encodedCredentials)
				.header("Content-Type", "application/json")
				.GET()
				.build();

		HttpResponse<String> response = HttpClient.newHttpClient()
				.send(request, HttpResponse.BodyHandlers.ofString());
		Program[] programs = mapper.readValue(response.body(), Program[].class);
		List<String> programDescriptions=new ArrayList<String>();
		for(Program i:programs){
			programDescriptions.add(i.getProgramType());
		}
		return new ResponseEntity(programDescriptions,HttpStatus.OK);
	}

	@RequestMapping(value="/vendors",method=RequestMethod.GET)
	public ResponseEntity getVendors() throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();

		String username = "APIUser";
		String password = "MTE5MF4qKl55NTN4dWVmc3k1M3h1ZWZz";
		String credentials = username + ":" + password;
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://sandbox.pha-web.com/api/vendor/v1/vendorList"))
				.header("Authorization", "Basic " + encodedCredentials)
				.header("Content-Type", "application/json")
				.GET()
				.build();

		HttpResponse<String> response = HttpClient.newHttpClient()
				.send(request, HttpResponse.BodyHandlers.ofString());
		Vendor[] vendors = mapper.readValue(response.body(), Vendor[].class);
		List<String> vendorNames=new ArrayList<String>();
		for(Vendor i:vendors){
			vendorNames.add(i.getBusinessName());
		}
		ResponseEntity responseEntity = new ResponseEntity(vendorNames, HttpStatus.OK);
		return responseEntity;
	}

	@RequestMapping(value="/billVendors",method=RequestMethod.GET)
	public BillVendor getBillVendors() throws IOException {
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		ObjectMapper mapper = new ObjectMapper();
		String sessionId = getBillSessionId();
		body.add("devKey","01PVVKPJTLRMTPNOI445");
		body.add("sessionId",sessionId);
		body.add("data","{\"start\":0,\"max\":999}");

		HttpEntity<MultiValueMap<String, Object>> requestEntity= new HttpEntity<>(body);

		String result = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/List/Vendor.json", requestEntity,
				String.class).getBody().toString();
		BillVendor vendor = mapper.readValue(result, BillVendor.class);
		return vendor;
	}

	@RequestMapping(value="/sessionId",method=RequestMethod.GET)
	public String getBillSessionId() throws IOException {
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		ObjectMapper mapper = new ObjectMapper();
		body.add("userName","sahar@momentum-worldwide.com");
		body.add("password","Momentum2024!");
		body.add("orgId","00802BJRJZVDEBRL5bi8");
		body.add("devKey","01PVVKPJTLRMTPNOI445");

		HttpEntity<MultiValueMap<String, Object>> requestEntity= new HttpEntity<>(body);

		String result = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Login.json", requestEntity,
				String.class).getBody().toString();
		System.out.println(result);
		Session session = mapper.readValue(result, Session.class);
		return session.getResponseData().getSessionId();
	}


	@RequestMapping(value="/accounts",method=RequestMethod.GET)
	public ResponseEntity getAccounts() throws IOException, InterruptedException {
			ObjectMapper mapper = new ObjectMapper();
		String encodedCredentials = getPhaWebToken();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://sandbox.pha-web.com/api/vendor/v1/accounts?programId=455"))
					.header("Authorization", "Basic " + encodedCredentials)
					.header("Content-Type", "application/json")
					.GET()
					.build();
			List<String> accountNames = new ArrayList<String>();
			HttpResponse<String> response = HttpClient.newHttpClient()
					.send(request, HttpResponse.BodyHandlers.ofString());
		AccountData[] accounts = mapper.readValue(response.body(), AccountData[].class);
			for (AccountData i : accounts)
				accountNames.add(i.getAccountName());
			ResponseEntity responseEntity = new ResponseEntity(accountNames, HttpStatus.OK);
			return responseEntity;
	}

	public String getPhaWebToken() throws IOException {
		String username = "APIUser";
		String password = "MTE5MF4qKl55NTN4dWVmc3k1M3h1ZWZz";
		String credentials = username + ":" + password;
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		return encodedCredentials;
	}

	@RequestMapping(value = "/createVendor", method = RequestMethod.GET)
	public String createVendor() throws IOException, InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String sessionId= getBillSessionId();
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		VendorData vd = new VendorData();
		vd.setName("Test");
		vd.setIsActive("1");
		vd.setEntity("Vendor");
		Data d = new Data();
		d.setObj(vd);
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(d);
        body.add("devKey", "01PVVKPJTLRMTPNOI445");
        body.add("sessionId", getBillSessionId());
        body.add("data", json);
        HttpEntity<Map> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Crud/Create/Vendor.json", request, Map.class);
        return response.getBody().toString();
	}


	@RequestMapping(value = "/createBill", method = RequestMethod.GET)
	public String createBill() throws IOException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String sessionId= getBillSessionId();
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		Bill bill = new Bill();
		bill.setEntity("Bill");
		bill.setInvoiceDate("2024-02-09");
		bill.setDueDate("2024-02-11");
		bill.setInvoiceNumber("INV-0002");
		bill.setVendorId("00902GKJZCACFSR2225l");
		bill.setIsActive("1");
		bill.seteBillCreated(true);
		bill.setSource("1");
		List<BillLineItem> bliList = new ArrayList<BillLineItem>();
		BillLineItem bli = new BillLineItem();
		bli.setEntity("BillLineItem");
		bli.setAmount(100);
		bli.setUnitPrice(100);
		bliList.add(bli);
		bill.setBillLineItems(bliList);
		BillClass bc = new BillClass();
		bc.setObj(bill);
		bc.setAllowDuplicateInvNum(true);
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(bc);
		body.add("devKey", "01PVVKPJTLRMTPNOI445");
		body.add("sessionId", getBillSessionId());
		body.add("data", json);
		HttpEntity<Map> request = new HttpEntity<>(body, headers);
		ResponseEntity<Map> response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Crud/Create/Bill.json", request, Map.class);
		if(response.getBody() != null){
			return response.getBody().toString();
		}
		return null;
	}

	@RequestMapping(value = "/uploadMultiMedia", method = RequestMethod.GET)
	public String uploadMultiMedia() throws IOException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String sessionId= getBillSessionId();
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		return null;
	}
}
