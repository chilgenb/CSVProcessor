package com.hilgenberg.csvprocessor.model;

public class Record {
    private String userId;
    private String name;

    private int version;

    private String insuranceCompanyName;

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
        return "CSVFile{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", insuranceCompanyName='" + insuranceCompanyName + '\'' +
                '}';
    }
}
