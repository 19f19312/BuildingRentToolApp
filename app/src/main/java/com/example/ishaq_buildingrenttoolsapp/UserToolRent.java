package com.example.ishaq_buildingrenttoolsapp;

import com.google.firebase.database.Exclude;

public class UserToolRent {
    public String tName;
    public String tPrice;
    public String tUrl;
    public String rName;
    public String rPhone;
    public String rAddress;
    public String rDays;
    public String rentDate;
    public String returnDate;

    public String mKey;

    public UserToolRent() {
    }

    public UserToolRent(String tName, String tPrice, String tUrl, String rName, String rPhone, String rAddress, String rDays, String rentDate, String returnDate) {
        this.tName = tName;
        this.tPrice = tPrice;
        this.tUrl = tUrl;
        this.rName = rName;
        this.rPhone = rPhone;
        this.rAddress = rAddress;
        this.rDays = rDays;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettPrice() {
        return tPrice;
    }

    public void settPrice(String tPrice) {
        this.tPrice = tPrice;
    }

    public String gettUrl() {
        return tUrl;
    }

    public void settUrl(String tUrl) {
        this.tUrl = tUrl;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrPhone() {
        return rPhone;
    }

    public void setrPhone(String rPhone) {
        this.rPhone = rPhone;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getrDays() {
        return rDays;
    }

    public void setrDays(String rDays) {
        this.rDays = rDays;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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
