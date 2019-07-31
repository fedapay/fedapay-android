package org.fedapay.fedalib.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTransactionStatus {

    @SerializedName("mode")
    @Expose
    private String mode;

    @SerializedName("token")
    @Expose
    private String token;

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
}
