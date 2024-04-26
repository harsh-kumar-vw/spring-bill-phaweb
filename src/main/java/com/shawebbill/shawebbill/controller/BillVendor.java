package com.shawebbill.shawebbill.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BillVendor {
    private int responseStatus;
    private String responseMessage;
    private List<VendorData> responseData;

    public int getResponseStatus() {
        return responseStatus;
    }

    @JsonProperty("response_status")
    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    @JsonProperty("response_message")
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public List<VendorData> getResponseData() {
        return responseData;
    }

    @JsonProperty("response_data")
    public void setResponseData(List<VendorData> responseData) {
        this.responseData = responseData;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class VendorData {
    public String vendor;
    public String entity;
	public String id;
	public String isActive;
	public String name;
	public String address1;
	public String addressCity;
	public String addressState;
	public String addressZip;
	public String addressCountry;
	public String email;
	public String phone;
    public String companyName;
    public String contactLastName;
    public String contactFirstName;


    @JsonProperty("vendor")
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("entity")
    public void setEntity(String entity) {
        this.entity = entity;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isActive")
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
    public VendorData obj;
    @JsonProperty("obj")
    public void setObj(VendorData obj) {
        this.obj = obj;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class RequestBody {
    public String data;
    public String devKey;
    public String sessionId;
    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("devKey")
    public void setDevKey(String devKey) {
        this.devKey = devKey;
    }

    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
