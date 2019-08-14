package org.fedapay.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.fedapay.fedalib.FedaPMT;
import org.fedapay.fedalib.FedaPay;
import org.fedapay.fedalib.Listenners.OnRequestLoadListener;
import org.fedapay.fedalib.Models.Currency;
import org.fedapay.fedalib.Models.Customer;
import org.fedapay.fedalib.Models.Requests.NewTransactionObject;
import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Transaction;
import org.fedapay.fedalib.Utils.Constants;
import org.fedapay.sample.Uils.BaseActivity;

public class MainActivity extends BaseActivity implements OnRequestLoadListener {
    public FedaPay fedaPay;
    private Button mPayer;
    public Transaction transaction;
    NewTransactionObject newTransactionObject;
    public Currency fedaCurrency, currency;
    Customer fedaCustomer;
    String FEDAPAY_API_KEY;
    int TRANSACTION_REQUEST_CODE = 100;
    int FEDAPAY_UI_PRIIMARY_COLOR;
    int FEDAPAY_UI_SECONDARY_COLOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fedaPay = new FedaPay(this);
        transaction = new Transaction();
        fedaPay.welcome(this, "Bienvenue Chez Feda PAY");
        //fedaPay.startPaiement(this);


        FEDAPAY_API_KEY = getString(R.string.FEDAPAY_API_KEY);

        mPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fedaCurrency = new Currency();
                currency = fedaCurrency.create("1","FCFA", "XOF","952", "null", "CFA", "1");

                fedaCustomer = new Customer();
                fedaCustomer.create("649");

                newTransactionObject = new NewTransactionObject();
                newTransactionObject.setData("10", "Payement effectue depuis l'application Android", fedaCustomer,currency);
                initTransaction();

            }
        });
    }

    private void initView() {
        mPayer = (Button) findViewById(R.id.btnPayer);
    }

    private void openPaiementDialog(Transaction transaction) {

        openAcitivtyForResult(FedaPMT.class, transaction, TRANSACTION_REQUEST_CODE, FEDAPAY_API_KEY);
    }

    private void initTransaction() {
        showProgressDialog("Creation de la transaction en cours...");
        transaction.create(FEDAPAY_API_KEY, this, newTransactionObject);
    }

    @Override
    public void onTransactionCreated(Transaction transaction) {
        hideProgressDialog();
        openPaiementDialog(transaction);
    }

    @Override
    public void onPaiementMaked(Transaction items) {

    }

    @Override
    public void onGetTransactionStatus(Transaction items) {

    }

    @Override
    public void onTokenCreated(TokenResponse items) {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onError(Constants.Errors errorCode, String error) {

        fedaPay.welcome(this, error);

    }

    @Override
    public void onTransactionError(Constants.Errors errorCode, String error) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TRANSACTION_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Intent returnIntent = new Intent();
                returnIntent = data;

                String result = returnIntent.getStringExtra("result");

                Toast.makeText(this, "transaction result : " + result, Toast.LENGTH_LONG).show();

            }
        }

    }
}
