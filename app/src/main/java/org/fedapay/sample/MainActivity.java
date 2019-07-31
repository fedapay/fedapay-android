package org.fedapay.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.fedapay.fedalib.FedaPMT;
import org.fedapay.fedalib.FedaPay;
import org.fedapay.fedalib.Listenners.OnRequestLoadListener;
import org.fedapay.fedalib.Models.Currency;
import org.fedapay.fedalib.Models.Customer;
import org.fedapay.fedalib.Models.Requests.StartTransaction;
import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Transaction;
import org.fedapay.fedalib.PaiementDialog;
import org.fedapay.fedalib.Utils.Constants;
import org.fedapay.sample.Uils.BaseActivity;

public class MainActivity extends BaseActivity implements OnRequestLoadListener {
    public FedaPay fedaPay;
    private Button mPayer;

    StartTransaction startTransaction;
    Currency fedaCurrency;
    Customer fedaCustomer;
    String FEDAPAY_API_KEY;
    int TRANSACTION_REQUEST_CODE = 100 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fedaPay = new FedaPay(this);
        fedaPay.welcome(this, "Bienvenue Chez Feda PAY");
        //fedaPay.startPaiement(this);


        FEDAPAY_API_KEY = getString(R.string.FEDAPAY_API_KEY);

        mPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fedaCurrency = new Currency();
                fedaCurrency.setId("1");
                fedaCurrency.setName("FCFA");
                fedaCurrency.setIso("XOF");
                fedaCurrency.setCode("952");
                fedaCurrency.setPrefix("null");
                fedaCurrency.setSuffix("CFA");
                fedaCurrency.setDiv("1");

                fedaCustomer = new Customer();
                fedaCustomer.setId("649");

                startTransaction = new StartTransaction();
                startTransaction.setAmount("10");
                startTransaction.setDescription("Payement effectue depuis l'application Android");
                startTransaction.setCurrency(fedaCurrency);
                startTransaction.setCustomer(fedaCustomer);

                startTransaction();

            }
        });
    }

    private void initView() {
        mPayer = (Button) findViewById(R.id.btnPayer);
    }

    private void openDialog(Transaction transaction) {

        //PaiementDialog.display(getSupportFragmentManager(), FEDAPAY_API_KEY, transaction);
        openAcitivtyForResult(FedaPMT.class, transaction, TRANSACTION_REQUEST_CODE, FEDAPAY_API_KEY);
    }
    private void startTransaction() {
//
//        Intent intent = new Intent(this, FedaPMT.class);
//        startActivity(intent);

        showProgressDialog("Creation de la transaction en cours...");
        fedaPay.createTransaction(FEDAPAY_API_KEY, this, startTransaction);


    }

    @Override
    public void onTransactionCreated(Transaction transaction) {
        hideProgressDialog();
        openDialog(transaction);
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

                Toast.makeText(this,"transaction result : " + result,Toast.LENGTH_LONG).show();

            }
        }

    }
}
