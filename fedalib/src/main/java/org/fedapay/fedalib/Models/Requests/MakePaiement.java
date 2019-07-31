package org.fedapay.fedalib.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.fedapay.fedalib.Models.PhoneNumber;

public class MakePaiement {

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("phone_number")
    @Expose
    private PhoneNumber phone_number;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PhoneNumber getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(PhoneNumber phone_number) {
        this.phone_number = phone_number;
    }
}
