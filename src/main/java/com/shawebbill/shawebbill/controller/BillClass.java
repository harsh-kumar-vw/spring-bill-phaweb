package com.shawebbill.shawebbill.controller;
import java.util.List;

public class BillClass {
    private Bill obj;
    private boolean allowDuplicateInvNum;

    public Bill getObj() {
        return obj;
    }

    public void setObj(Bill obj) {
        this.obj = obj;
    }

    public boolean isAllowDuplicateInvNum() {
        return allowDuplicateInvNum;
    }

    public void setAllowDuplicateInvNum(boolean allowDuplicateInvNum) {
        this.allowDuplicateInvNum = allowDuplicateInvNum;
    }
}

class Bill {
    private String entity;
    private List<BillLineItem> billLineItems;
    private String isActive;
    private String invoiceNumber;
    private String invoiceDate;
    private String dueDate;
    private String description;
    private boolean eBillCreated;
    private String source;
    private String vendorId;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<BillLineItem> getBillLineItems() {
        return billLineItems;
    }

    public void setBillLineItems(List<BillLineItem> billLineItems) {
        this.billLineItems = billLineItems;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean iseBillCreated() {
        return eBillCreated;
    }

    public void seteBillCreated(boolean eBillCreated) {
        this.eBillCreated = eBillCreated;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
}

class BillLineItem {
    private String entity;
    private int amount;
    private int quantity;
    private int unitPrice;
    private boolean useSmartApprovers;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public boolean isUseSmartApprovers() {
        return useSmartApprovers;
    }

    public void setUseSmartApprovers(boolean useSmartApprovers) {
        this.useSmartApprovers = useSmartApprovers;
    }
}
