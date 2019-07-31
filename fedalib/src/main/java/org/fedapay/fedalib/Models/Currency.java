package org.fedapay.fedalib.Models;

import com.google.gson.annotations.SerializedName;


public class Currency {


    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("iso")
    String iso;

    @SerializedName("code")
    String code;

    @SerializedName("prefix")
    String prefix;

    @SerializedName("suffix")
    String suffix;

    @SerializedName("div")
    String div;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }
}
