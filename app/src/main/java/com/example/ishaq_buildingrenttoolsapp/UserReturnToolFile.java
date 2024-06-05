package com.example.ishaq_buildingrenttoolsapp;

import com.google.firebase.database.Exclude;

public class UserReturnToolFile {
    public String returntName;
    public String returntPrice;
    public String returntUrl;
    public String returnrName;
    public String returnrPhone;
    public String returnrAddress;
    public String returnrDays;
    public String returnrentDate;
    public String returnreturnDate;

    public String mKey;

    public UserReturnToolFile() {
    }

    public UserReturnToolFile(String returntName, String returntPrice, String returntUrl, String returnrName, String returnrPhone, String returnrAddress, String returnrDays, String returnrentDate, String returnreturnDate) {
        this.returntName = returntName;
        this.returntPrice = returntPrice;
        this.returntUrl = returntUrl;
        this.returnrName = returnrName;
        this.returnrPhone = returnrPhone;
        this.returnrAddress = returnrAddress;
        this.returnrDays = returnrDays;
        this.returnrentDate = returnrentDate;
        this.returnreturnDate = returnreturnDate;
    }

    public String getReturntName() {
        return returntName;
    }

    public void setReturntName(String returntName) {
        this.returntName = returntName;
    }

    public String getReturntPrice() {
        return returntPrice;
    }

    public void setReturntPrice(String returntPrice) {
        this.returntPrice = returntPrice;
    }

    public String getReturntUrl() {
        return returntUrl;
    }

    public void setReturntUrl(String returntUrl) {
        this.returntUrl = returntUrl;
    }

    public String getReturnrName() {
        return returnrName;
    }

    public void setReturnrName(String returnrName) {
        this.returnrName = returnrName;
    }

    public String getReturnrPhone() {
        return returnrPhone;
    }

    public void setReturnrPhone(String returnrPhone) {
        this.returnrPhone = returnrPhone;
    }

    public String getReturnrAddress() {
        return returnrAddress;
    }

    public void setReturnrAddress(String returnrAddress) {
        this.returnrAddress = returnrAddress;
    }

    public String getReturnrDays() {
        return returnrDays;
    }

    public void setReturnrDays(String returnrDays) {
        this.returnrDays = returnrDays;
    }

    public String getReturnrentDate() {
        return returnrentDate;
    }

    public void setReturnrentDate(String returnrentDate) {
        this.returnrentDate = returnrentDate;
    }

    public String getReturnreturnDate() {
        return returnreturnDate;
    }

    public void setReturnreturnDate(String returnreturnDate) {
        this.returnreturnDate = returnreturnDate;
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
