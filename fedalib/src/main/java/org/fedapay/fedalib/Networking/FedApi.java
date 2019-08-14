package org.fedapay.fedalib.Networking;
import org.fedapay.fedalib.BuildConfig;
import org.fedapay.fedalib.Models.Requests.GetTransactionStatus;
import org.fedapay.fedalib.Models.Requests.MakePaiement;
import org.fedapay.fedalib.Models.Requests.NewTransactionObject;
import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Responses.TransactionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FedApi {
    String BASE_URL = BuildConfig.BASE_URL;

    //Créer une transaction
    @POST("transactions")
    Call<TransactionResponse> newTransaction(@Body NewTransactionObject newTransactionObjectRequest);
    //Générer un token de paiement
    @POST("transactions/{id}/token")
    Call<TokenResponse> getToken(@Path("id") String token);
    //Envoyer une requête de paiement mobile money
    @POST("{mode}")
    Call<TransactionResponse> newPaiement(@Body MakePaiement paiementRequest, @Path("mode") String mode);
    //Récupérer le status
    @POST("{mode}/callback")
    Call<TransactionResponse> checkStatus(@Body GetTransactionStatus getStatusRequest, @Path("mode") String mode);


}
