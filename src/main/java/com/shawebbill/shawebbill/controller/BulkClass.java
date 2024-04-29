package com.shawebbill.shawebbill.controller;

import java.util.List;

public class BulkClass {
    private List<BulkItem> bulk;

    public List<BulkItem> getBulk() {
        return bulk;
    }

    public void setBulk(List<BulkItem> bulk) {
        this.bulk = bulk;
    }
}

class BulkItem {
    private Obj obj;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }
}

class Obj {
    private String entity;
    private String name;
    private String isActive;
    private String accountType;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
