package com.shawebbill.shawebbill.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DistributionResponse {
    private List<DistributionListItem> distributionList;

    public List<DistributionListItem> getDistributionList() {
        return distributionList;
    }

    public void setDistributionList(List<DistributionListItem> distributionList) {
        this.distributionList = distributionList;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class DistributionListItem {
    private int distributionListId;
    private String distributionListType;
    private String distributionComponent;
    private String description;
    private String notes;
    private List<Distribution> distributions;

    public int getDistributionListId() {
        return distributionListId;
    }

    public void setDistributionListId(int distributionListId) {
        this.distributionListId = distributionListId;
    }

    public String getDistributionListType() {
        return distributionListType;
    }

    public void setDistributionListType(String distributionListType) {
        this.distributionListType = distributionListType;
    }

    public String getDistributionComponent() {
        return distributionComponent;
    }

    public void setDistributionComponent(String distributionComponent) {
        this.distributionComponent = distributionComponent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Distribution> getDistributions() {
        return distributions;
    }

    public void setDistributions(List<Distribution> distributions) {
        this.distributions = distributions;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Distribution {
    private int distributionId;
    private int accountId;
    private double percentage;
    private double utilityUsage;
    private int programId;
    private int projectId;

    public int getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(int distributionId) {
        this.distributionId = distributionId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getUtilityUsage() {
        return utilityUsage;
    }

    public void setUtilityUsage(double utilityUsage) {
        this.utilityUsage = utilityUsage;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}