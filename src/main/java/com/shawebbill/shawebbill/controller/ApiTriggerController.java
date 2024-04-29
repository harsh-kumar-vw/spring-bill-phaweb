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
@RequestMapping(value = "/sha")
public class ApiTriggerController {
	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping(value = "/programs", method = RequestMethod.GET)
	public Program[] getPrograms() throws IOException, InterruptedException {
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

		HttpResponse < String > response = HttpClient.newHttpClient()
				.send(request, HttpResponse.BodyHandlers.ofString());
		Program[] programs = mapper.readValue(response.body(), Program[].class);
		return programs;
	}

	@RequestMapping(value = "/vendors", method = RequestMethod.GET)
	public VendorResponse[] getVendors() throws IOException, InterruptedException {
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

		HttpResponse < String > response = HttpClient.newHttpClient()
				.send(request, HttpResponse.BodyHandlers.ofString());
		VendorResponse[] vendors = mapper.readValue(response.body(), VendorResponse[].class);
		return vendors;
	}

	@RequestMapping(value = "/sessionId", method = RequestMethod.GET)
	public String getBillSessionId() throws IOException {
		MultiValueMap < String, Object > body = new LinkedMultiValueMap < > ();
		ObjectMapper mapper = new ObjectMapper();
		body.add("userName", "sahar@momentum-worldwide.com");
		body.add("password", "Momentum2024!");
		body.add("orgId", "00802BJRJZVDEBRL5bi8");
		body.add("devKey", "01PVVKPJTLRMTPNOI445");

		HttpEntity < MultiValueMap < String, Object >> requestEntity = new HttpEntity < > (body);

		String result = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Login.json", requestEntity,
				String.class).getBody().toString();
		System.out.println(result);
		Session session = mapper.readValue(result, Session.class);
		return session.getResponseData().getSessionId();
	}


	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public AccountData[] getAccounts() throws IOException, InterruptedException {
		ObjectMapper mapper = new ObjectMapper();
		String encodedCredentials = getPhaWebToken();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://sandbox.pha-web.com/api/vendor/v1/accounts?programId=455"))
				.header("Authorization", "Basic " + encodedCredentials)
				.header("Content-Type", "application/json")
				.GET()
				.build();
		List < String > accountNames = new ArrayList < String > ();
		HttpResponse < String > response = HttpClient.newHttpClient()
				.send(request, HttpResponse.BodyHandlers.ofString());
		AccountData[] accounts = mapper.readValue(response.body(), AccountData[].class);
		for (AccountData i: accounts)
			accountNames.add(i.getAccountName());
		ResponseEntity responseEntity = new ResponseEntity(accountNames, HttpStatus.OK);
		return accounts;
	}

	public String getPhaWebToken() throws IOException {
		String username = "APIUser";
		String password = "MTE5MF4qKl55NTN4dWVmc3k1M3h1ZWZz";
		String credentials = username + ":" + password;
		String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
		return encodedCredentials;
	}

	@RequestMapping(value = "/createVendor", method = RequestMethod.GET)
	public String createVendor(VendorResponse vendor) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String sessionId = getBillSessionId();
		MultiValueMap < String, Object > body = new LinkedMultiValueMap < > ();
		VendorData vd = new VendorData();
		vd.setName(vendor.getBusinessName());
		vd.setIsActive("1");
		vd.setEntity("Vendor");
		List<VendorResponse.Email> email = vendor.getVendorEmailList();
		vd.address1 = vendor.getAddressLine1();
		vd.addressCity = vendor.getAddressCity();
		vd.addressState = vendor.getAddressState();
		String emailAddress ="";
		if(email != null && email.size()>0){
			for(VendorResponse.Email em: email){
				emailAddress=em.getEmailAddress();
			}
		}
		vd.email = emailAddress;
		Data d = new Data();
		d.setObj(vd);
		ObjectWriter ow = new ObjectMapper().writer();
		String json = ow.writeValueAsString(d);
		body.add("devKey", "01PVVKPJTLRMTPNOI445");
		body.add("sessionId", sessionId);
		body.add("data", json);
		HttpEntity < Map > request = new HttpEntity < > (body, headers);
		ResponseEntity < Map > response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Crud/Create/Vendor.json", request, Map.class);
		return response.getBody().toString();
	}

	@RequestMapping(value = "/billVendors", method = RequestMethod.GET)
	public BillVendor getBillVendors() throws IOException {
		try {
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			ObjectMapper mapper = new ObjectMapper();
			String sessionId = getBillSessionId();
			body.add("devKey", "01PVVKPJTLRMTPNOI445");
			body.add("sessionId", sessionId);
			body.add("data", "{\"start\":0,\"max\":999}");
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body);
			String result = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/List/Vendor.json", requestEntity,
					String.class).getBody().toString();
			BillVendor vendor = mapper.readValue(result, BillVendor.class);
			return vendor;
		}catch(Exception ex){
			throw ex;
		}
	}


	@RequestMapping(value = "/createBill", method = RequestMethod.GET)
	public String createBill() throws IOException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		String sessionId = getBillSessionId();
		MultiValueMap < String, Object > body = new LinkedMultiValueMap < > ();
		Bill bill = new Bill();
		bill.setEntity("Bill");
		bill.setInvoiceDate("2024-02-09");
		bill.setDueDate("2024-02-11");
		bill.setInvoiceNumber("INV-0002");
		bill.setVendorId("00902GKJZCACFSR2225l");
		bill.setIsActive("1");
		bill.seteBillCreated(true);
		bill.setSource("1");
		List < BillLineItem > bliList = new ArrayList < BillLineItem > ();
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
		HttpEntity < Map > request = new HttpEntity < > (body, headers);
		ResponseEntity < Map > response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Crud/Create/Bill.json", request, Map.class);
		if (response.getBody() != null) {
			return response.getBody().toString();
		}
		return null;
	}

	@RequestMapping(value = "/uploadMultiMedia", method = RequestMethod.GET)
	public String uploadMultiMedia() throws IOException, InterruptedException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		String sessionId = getBillSessionId();
		MultiValueMap < String, Object > body = new LinkedMultiValueMap < > ();
		HttpEntity < Map > request = new HttpEntity < > (body, headers);
		ResponseEntity < Map > response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Crud/Create/Bill.json", request, Map.class);
		if (response.getBody() != null) {
			body.add("data", "{\"id\":\"00n02VHMDEPFXQA99uq6\",\"fileName\":\"TestFile3.pdf\"}");
		}
		return null;
	}

	@RequestMapping(value = "/createPhaWebVendor", method = RequestMethod.GET)
	public String createPhaWebVendor(VendorData vd) throws IOException, InterruptedException {
		MultiValueMap < String, Object > body = new LinkedMultiValueMap < > ();
		try {
			String username = "APIUser";
			String password = "MTE5MF4qKl55NTN4dWVmc3k1M3h1ZWZz";
			String credentials = username + ":" + password;
			String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
			HttpHeaders headers = new HttpHeaders();
			headers.setBasicAuth(encodedCredentials);
			headers.setContentType(MediaType.APPLICATION_JSON);
			VendorResponse vr = new VendorResponse();
			vr.setVendorId("1");
			if(vd.companyName != null){
				vr.setBusinessName(vd.companyName);
			}else{
				vr.setBusinessName("sample");
			}
			if(vd.contactFirstName != null){
				vr.setFirstName(vd.contactFirstName);
			}else{
				vr.setFirstName("sample");
			}
			if(vd.contactLastName != null){
				vr.setLastName(vd.contactLastName);
			}else{
				vr.setLastName("sample");
			}
			vr.setVendorCode("sample");
			if(vd.address1 != null){
				vr.setAddressLine1(vd.address1);
			}else{
				vr.setAddressLine1("sample");
			}
			vr.setAddressUnit("sample");
			if(vd.addressCity != null){
				vr.setAddressCity(vd.addressCity);
			}else{
				vr.setAddressCity("sample");
			}
			if(vd.addressState != null){
				vr.setAddressState(vd.addressState);
			}else{
				vr.setAddressState("sample");
			}
			if(vd.addressZip != null){
				vr.setAddressZipCode(vd.addressZip);
			}else{
				vr.setAddressZipCode("sample");
			}
			if(vd.phone != null){
				vr.setPhoneNumber(vd.phone);
			}else{
				vr.setPhoneNumber("9876543210");
			}
			vr.setDefaultPaymentTermId(1);
			VendorResponse.Email email = new VendorResponse.Email();
			if(vd.email != null){
				email.setEmailAddress(vd.email);
			}else{
				email.setEmailAddress("email@email.com");
			}
			List < VendorResponse.Email > emailList = new ArrayList <>();
			emailList.add(email);
			vr.setVendorEmailList(emailList);
			HttpEntity < VendorResponse > request = new HttpEntity <>(vr, headers);
			ResponseEntity < Map > response = restTemplate.postForEntity("https://sandbox.pha-web.com/api/vendor/v1/vendor", request, Map.class);
			return response.getBody().toString();
		} catch (Exception ex) {
			return ex.getMessage().toString();
		}
	}

	@RequestMapping(value = "/createPhaWebVendors", method = RequestMethod.GET)
	public String createPhaWebVendors() throws IOException, InterruptedException {
		try {
			BillVendor billvendor = getBillVendors();
			List<VendorData> vendors = billvendor.getResponseData();
			for (VendorData vd : vendors) {
				createPhaWebVendor(vd);
			}
			return "null";
		}catch (Exception ex){
			return ex.getMessage().toString();
		}
	}

	@RequestMapping(value = "/createBillVendors", method = RequestMethod.GET)
	public String createBillVendors() throws IOException, InterruptedException {
		try {
			VendorResponse[] vendors = getVendors();
			List<String> errors = new ArrayList<>();
			for (VendorResponse vd : vendors) {
				createVendor(vd);
			}
			return "Vendors Created";
		}catch (Exception ex){
			return ex.getMessage().toString();
		}
	}

	@RequestMapping(value = "/createDepartment", method = RequestMethod.GET)
	public String createDepartment(Program program) throws IOException {
		String json="" ;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			String sessionId = getBillSessionId();
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			ObjectWriter ow = new ObjectMapper().writer();
			body.add("devKey", "01PVVKPJTLRMTPNOI445");
			body.add("sessionId", sessionId);
			DepartmentData departmentData = new DepartmentData();
			departmentData.setEntity("Department");
			departmentData.setName(program.getProgramType());
			departmentData.setDescription(program.getProgramDescription());
			departmentData.setIsActive("1");
			ObjData obj = new ObjData();
			obj.setObj(departmentData);
			json = ow.writeValueAsString(obj);
			body.add("data", json);
			HttpEntity<Map> request = new HttpEntity<>(body, headers);
			ResponseEntity<Map> response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Crud/Create/Department.json", request, Map.class);
			return response.getBody().toString();
		}catch (Exception ex){
			return ex.getMessage().toString();
		}
	}
	@RequestMapping(value = "/createDepartments", method = RequestMethod.GET)
	public String createDepartments() throws IOException {
		try {
			Program[] programs = getPrograms();
			for (Program program : programs) {
				createDepartment(program);
			}
			return "Programs created";
		} catch (Exception ex) {
			return ex.getMessage().toString();
		}
	}

	@RequestMapping(value = "/createAccount", method = RequestMethod.GET)
	public String createAccount() throws IOException,InterruptedException {
		String json="";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			String sessionId = getBillSessionId();
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			ObjectWriter ow = new ObjectMapper().writer();
			body.add("devKey", "01PVVKPJTLRMTPNOI445");
			body.add("sessionId", sessionId);
			BulkClass bc = new BulkClass();
			AccountData[] accountData = getAccounts();
			List<BulkItem> biList = createBulkItems(accountData);
			bc.setBulk(biList);
			body.add("data",ow.writeValueAsString(bc));
			HttpEntity<Map> request = new HttpEntity<>(body, headers);
			ResponseEntity<Map> response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Bulk/Crud/Create/ChartOfAccount.json", request, Map.class);
			return response.getBody().toString();
		} catch (Exception ex) {
			return ex.getMessage().toString();
		}
	}

	public List<BulkItem> createBulkItems(AccountData[] accounts) throws IOException,InterruptedException {
		List<BulkItem> biList = new ArrayList<>();
		for(AccountData ad: accounts) {
			Obj ob = new Obj();
			ob.setEntity("ChartOfAccount");
			ob.setAccountType("1");
			ob.setName(ad.getAccountName());
			ob.setIsActive("1");
			BulkItem bi = new BulkItem();
			bi.setObj(ob);
			biList.add(bi);
		}
		return biList;
	}

	@RequestMapping(value = "/getDistributions", method = RequestMethod.GET)
	public DistributionListItem[] getDistributions() throws IOException,InterruptedException{
		try {
			ObjectMapper mapper = new ObjectMapper();
			String encodedCredentials = getPhaWebToken();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://sandbox.pha-web.com/api/vendor/v1/distributions?programId=-1"))
					.header("Authorization", "Basic " + encodedCredentials)
					.header("Content-Type", "application/json")
					.GET()
					.build();
			HttpResponse<String> response = HttpClient.newHttpClient()
					.send(request, HttpResponse.BodyHandlers.ofString());
			DistributionListItem[] distributionListItems = mapper.readValue(response.body(), DistributionListItem[].class);
			return distributionListItems;
		}catch (Exception ex){
			return null;
		}
	}

	@RequestMapping(value = "/createClasses", method = RequestMethod.GET)
	public String createAcctgClasses() throws IOException,InterruptedException {
		String json="";
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			String sessionId = getBillSessionId();
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			ObjectWriter ow = new ObjectMapper().writer();
			body.add("devKey", "01PVVKPJTLRMTPNOI445");
			body.add("sessionId", sessionId);
			BulkClass bc = new BulkClass();
			DistributionListItem[] distributionListItems = getDistributions();
			List<Distribution> distributions = new ArrayList<>();
			for(DistributionListItem dli: distributionListItems){
				distributions.addAll(dli.getDistributions());
			}
			List<BulkItem> biList = createBulkDistributions(distributions);
			bc.setBulk(biList);
			body.add("data",ow.writeValueAsString(bc));
			HttpEntity<Map> request = new HttpEntity<>(body, headers);
			ResponseEntity<Map> response = restTemplate.postForEntity("https://app-stage02.us.bill.com/api/v2/Bulk/Crud/Create/ActgClass.json", request, Map.class);
			return response.getBody().toString();
		} catch (Exception ex) {
			return ex.getMessage().toString();
		}
	}

	public List<BulkItem> createBulkDistributions(List<Distribution> distributions) throws IOException,InterruptedException {
		List<BulkItem> biList = new ArrayList<>();
		for(Distribution d: distributions) {
			Obj ob = new Obj();
			ob.setEntity("ActgClass");
			ob.setAccountType("1");
			ob.setName(String.valueOf(d.getDistributionId()));
			ob.setIsActive("1");
			BulkItem bi = new BulkItem();
			bi.setObj(ob);
			biList.add(bi);
		}
		return biList;
	}
}

