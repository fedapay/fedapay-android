package org.fedapay.fedalib.Models;

import com.google.gson.annotations.SerializedName;


public class AppConfig {


    @SerializedName("merchant_id")
    String merchantId;

    @SerializedName("channel")
    String channel;

    @SerializedName("industry_type")
    String industryType;

    @SerializedName("website")
    String website;

    public AppConfig() {
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getChannel() {
        return channel;
    }

    public String getIndustryType() {
        return industryType;
    }

    public String getWebsite() {
        return website;
    }
}
