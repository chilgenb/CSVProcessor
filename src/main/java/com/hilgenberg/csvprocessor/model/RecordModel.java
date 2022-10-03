package com.hilgenberg.csvprocessor.model;
public class RecordModel{
    private String userId;
    private String name;

    private int version;

    private String insuranceCompanyName;

    public RecordModel() {
        super();
    }

    public RecordModel(String userId, String name, int version, String insuranceCompanyName) {
        this();
        this.userId = userId;
        this.name = name;
        this.version = version;
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    @Override
    public String toString() {
        return userId + ", " + name + ", " + version + ", " + insuranceCompanyName;
    }
}
