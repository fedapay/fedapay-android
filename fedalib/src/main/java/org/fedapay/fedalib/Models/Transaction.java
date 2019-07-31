package org.fedapay.fedalib.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Transaction implements Serializable {

    @SerializedName("klass")
    String klass;

    @SerializedName("id")
    String id;

    @SerializedName("reference")
    String reference;

    @SerializedName("amount")
    String amount;

    @SerializedName("description")
    String description;

    @SerializedName("callback_url")
    String callback_url;

    @SerializedName("status")
    String status;

    @SerializedName("customer_id")
    String customer_id;

    @SerializedName("currency_id")
    String currency_id;

    @SerializedName("mode")
    String mode;

    @SerializedName("operation")
    String operation;

//    @SerializedName("metadata")
//    String metadata;

    @SerializedName("commission")
    String commission;

    @SerializedName("fees")
    String fees;

    @SerializedName("fixed_commission")
    String fixed_commission;

    @SerializedName("amount_transferred")
    String amount_transferred;

    @SerializedName("created_at")
    String created_at;

    @SerializedName("updated_at")
    String updated_at;

    @SerializedName("approved_at")
    String approved_at;

    @SerializedName("canceled_at")
    String canceled_at;

    @SerializedName("declined_at")
    String declined_at;

    @SerializedName("refunded_at")
    String refunded_at;

    @SerializedName("transferred_at")
    String transferred_at;

    @SerializedName("deleted_at")
    String deleted_at;

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public void setCurrency_id(String currency_id) {
        this.currency_id = currency_id;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

//    public String getMetadata() {
//        return metadata;
//    }
//
//    public void setMetadata(String metadata) {
//        this.metadata = metadata;
//    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getFixed_commission() {
        return fixed_commission;
    }

    public void setFixed_commission(String fixed_commission) {
        this.fixed_commission = fixed_commission;
    }

    public String getAmount_transferred() {
        return amount_transferred;
    }

    public void setAmount_transferred(String amount_transferred) {
        this.amount_transferred = amount_transferred;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getApproved_at() {
        return approved_at;
    }

    public void setApproved_at(String approved_at) {
        this.approved_at = approved_at;
    }

    public String getCanceled_at() {
        return canceled_at;
    }

    public void setCanceled_at(String canceled_at) {
        this.canceled_at = canceled_at;
    }

    public String getDeclined_at() {
        return declined_at;
    }

    public void setDeclined_at(String declined_at) {
        this.declined_at = declined_at;
    }

    public String getRefunded_at() {
        return refunded_at;
    }

    public void setRefunded_at(String refunded_at) {
        this.refunded_at = refunded_at;
    }

    public String getTransferred_at() {
        return transferred_at;
    }

    public void setTransferred_at(String transferred_at) {
        this.transferred_at = transferred_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }
}
