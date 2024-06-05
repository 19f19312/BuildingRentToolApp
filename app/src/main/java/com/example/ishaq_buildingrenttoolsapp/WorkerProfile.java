package com.example.ishaq_buildingrenttoolsapp;

import com.google.firebase.database.Exclude;

public class WorkerProfile {
    public String workerName;
    public String workerPhone;
    public String workerAddress;
    public String workerGender;
    public String workerSkills;
    public String workerStatus;

    public String mKey;

    public WorkerProfile() {
    }

    public WorkerProfile(String workerName, String workerPhone, String workerAddress, String workerGender, String workerSkills, String workerStatus) {
        this.workerName = workerName;
        this.workerPhone = workerPhone;
        this.workerAddress = workerAddress;
        this.workerGender = workerGender;
        this.workerSkills = workerSkills;
        this.workerStatus = workerStatus;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerPhone() {
        return workerPhone;
    }

    public void setWorkerPhone(String workerPhone) {
        this.workerPhone = workerPhone;
    }

    public String getWorkerAddress() {
        return workerAddress;
    }

    public void setWorkerAddress(String workerAddress) {
        this.workerAddress = workerAddress;
    }

    public String getWorkerGender() {
        return workerGender;
    }

    public void setWorkerGender(String workerGender) {
        this.workerGender = workerGender;
    }

    public String getWorkerSkills() {
        return workerSkills;
    }

    public void setWorkerSkills(String workerSkills) {
        this.workerSkills = workerSkills;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

    public void setWorkerStatus(String workerStatus) {
        this.workerStatus = workerStatus;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }
    @Exclude
    public void setKey(String Key) {
        mKey = Key;
    }
}
