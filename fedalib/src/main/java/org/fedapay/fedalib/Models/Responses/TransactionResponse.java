package org.fedapay.fedalib.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.fedapay.fedalib.Models.Transaction;

public class TransactionResponse {

    @SerializedName("message")
    @Expose
    private String message;


    @SerializedName("v1/transaction")
    @Expose
    private Transaction transaction;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
