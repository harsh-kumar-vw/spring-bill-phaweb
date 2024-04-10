package com.shawebbill.shawebbill.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Session {
    private int responseStatus;
    private String responseMessage;
    private ResponseData responseData;

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

    public ResponseData getResponseData() {
        return responseData;
    }

    @JsonProperty("response_data")
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }		  
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ResponseData {
    private String apiEndPoint;
    private String sessionId;
    private String orgId;
    private String usersId;

    public String getApiEndPoint() {
        return apiEndPoint;
    }

    @JsonProperty("apiEndPoint")
    public void setApiEndPoint(String apiEndPoint) {
        this.apiEndPoint = apiEndPoint;
    }

    public String getSessionId() {
        return sessionId;
    }

    @JsonProperty("sessionId")
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOrgId() {
        return orgId;
    }

    @JsonProperty("orgId")
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getUsersId() {
        return usersId;
    } 
}