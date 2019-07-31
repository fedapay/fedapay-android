package org.fedapay.fedalib.Listenners;

import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Transaction;
import org.fedapay.fedalib.Utils.Constants;

public interface OnRequestLoadListener {

    void onTransactionCreated(Transaction items);

    void onPaiementMaked(Transaction items);

    void onGetTransactionStatus(Transaction items);

    void onTokenCreated(TokenResponse items);

    void onSuccess(String msg);

    void onError(Constants.Errors errorCode, String error);

    void onTransactionError(Constants.Errors errorCode, String error);
}