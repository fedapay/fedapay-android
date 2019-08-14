package org.fedapay.fedalib.Models.Requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.fedapay.fedalib.Models.Currency;
import org.fedapay.fedalib.Models.Customer;

public class NewTransactionObject {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("amount")
    @Expose
    private String amount;

    @SerializedName("customer")
    @Expose
    private Customer customer;

    @SerializedName("currency")
    @Expose
    private Currency currency;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public NewTransactionObject setData(String amount, String description, Customer customer, Currency currency) {
        this.description = description;
        this.amount = amount;
        this.customer = customer;
        this.currency = currency;
        return this;
    }
}
