package com.shawebbill.shawebbill.controller;

import java.util.List;

public class VendorResponse {
    private String vendorId;
    private String businessName;
    private String firstName;
    private String lastName;
    private String vendorCode;
    private String addressLine1;
    private String addressUnit;
    private String addressLine2;
    private String addressCity;
    private String addressState;
    private String addressZipCode;
    private String addressZipCodeExt;
    private String ssnTin;
    private String contactName;
    private String phoneNumber;
    private List<Email> vendorEmailList;
    private int defaultPaymentTermId;
    private int defaultDistributionListId;
    private String defaultCarrier;
    private String poAccountNumber;
    private boolean useAsUtilityCompany;

    // Getters and setters
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressUnit() {
        return addressUnit;
    }

    public void setAddressUnit(String addressUnit) {
        this.addressUnit = addressUnit;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(String addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getAddressZipCodeExt() {
        return addressZipCodeExt;
    }

    public void setAddressZipCodeExt(String addressZipCodeExt) {
        this.addressZipCodeExt = addressZipCodeExt;
    }

    public String getSsnTin() {
        return ssnTin;
    }

    public void setSsnTin(String ssnTin) {
        this.ssnTin = ssnTin;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Email> getVendorEmailList() {
        return vendorEmailList;
    }

    public void setVendorEmailList(List<Email> vendorEmailList) {
        this.vendorEmailList = vendorEmailList;
    }

    public int getDefaultPaymentTermId() {
        return defaultPaymentTermId;
    }

    public void setDefaultPaymentTermId(int defaultPaymentTermId) {
        this.defaultPaymentTermId = defaultPaymentTermId;
    }

    public int getDefaultDistributionListId() {
        return defaultDistributionListId;
    }

    public void setDefaultDistributionListId(int defaultDistributionListId) {
        this.defaultDistributionListId = defaultDistributionListId;
    }

    public String getDefaultCarrier() {
        return defaultCarrier;
    }

    public void setDefaultCarrier(String defaultCarrier) {
        this.defaultCarrier = defaultCarrier;
    }

    public String getPoAccountNumber() {
        return poAccountNumber;
    }

    public void setPoAccountNumber(String poAccountNumber) {
        this.poAccountNumber = poAccountNumber;
    }

    public boolean isUseAsUtilityCompany() {
        return useAsUtilityCompany;
    }

    public void setUseAsUtilityCompany(boolean useAsUtilityCompany) {
        this.useAsUtilityCompany = useAsUtilityCompany;
    }

    // Inner class for email
    public static class Email {
        private String emailAddress;

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }
    }
}
