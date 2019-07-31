package org.fedapay.fedalib.Models;

import com.google.gson.annotations.SerializedName;


public class Customer {


    @SerializedName("id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
