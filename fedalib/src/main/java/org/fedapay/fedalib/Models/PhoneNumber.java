package org.fedapay.fedalib.Models;

import com.google.gson.annotations.SerializedName;


public class PhoneNumber {


    @SerializedName("number")
    String number;

    @SerializedName("country")
    String country;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
