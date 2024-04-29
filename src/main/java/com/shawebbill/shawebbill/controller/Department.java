package com.shawebbill.shawebbill.controller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Department {
    private int response_status;
    private String response_message;
    private List<Data> response_data;

    public int getResponse_status() {
        return response_status;
    }

    public void setResponse_status(int response_status) {
        this.response_status = response_status;
    }

    public String getResponse_message() {
        return response_message;
    }

    public void setResponse_message(String response_message) {
        this.response_message = response_message;
    }

    public List<Data> getResponse_data() {
        return response_data;
    }

    public void setResponse_data(List<Data> response_data) {
        this.response_data = response_data;
    }
}
class ObjData {
    public DepartmentData obj;
    public void setObj(DepartmentData obj) {
        this.obj = obj;
    }
}

class Bulk {
    public List<DepartmentData> obj;
    public void setBulk(List<DepartmentData> obj) {
        this.obj = obj;
    }
}

class DepartmentData {
    private String entity;
    private String id;
    private String name;
    private String shortName;
    private String isActive;
    private String description;
    private String parentDepartmentId;
    private String createdTime;
    private String updatedTime;
    private String mergedIntoId;
    private String accountType;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getMergedIntoId() {
        return mergedIntoId;
    }

    public void setMergedIntoId(String mergedIntoId) {
        this.mergedIntoId = mergedIntoId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
