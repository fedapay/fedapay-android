package org.fedapay.fedalib;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.fedapay.fedalib.Listenners.OnRequestLoadListener;
import org.fedapay.fedalib.Models.PhoneNumber;
import org.fedapay.fedalib.Models.Requests.GetTransactionStatus;
import org.fedapay.fedalib.Models.Requests.MakePaiement;
import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Transaction;
import org.fedapay.fedalib.Utils.Constants;
import org.fedapay.fedalib.Utils.FedaBaseActivity;

import static android.view.View.VISIBLE;
import static org.fedapay.fedalib.Utils.Constants.FEDA_TRANSACTION_CANCELED;
import static org.fedapay.fedalib.Utils.Constants.FEDA_TRANSACTION_PENDDING;
import static org.fedapay.fedalib.Utils.Constants.FEDA_TRANSACTION_SUCCESS;

public class FedaPMT extends FedaBaseActivity implements View.OnClickListener, OnRequestLoadListener {

    static String fedaApiKey;
    static Transaction fedaPayTransaction;
    static AlertDialog.Builder dialogBuilder;
    static AlertDialog dialog;
    FedaPay fedaPay;
    String mode, phone, country, transactionToken;
    MakePaiement makePaiementRequest;
    private ImageView mMLogo;
    private TextView mMDescription;
    private TextView mMTransactionRef;
    private TextView mMAmmount;
    private Spinner mOperator;
    private EditText mCountryCode;
    private EditText mPhoneNumber;
    private Button mStartPaiement;

    public static void SetTransationOkView(View view, int img, int status) {

        ImageView logo = view.findViewById(R.id.logo);
        logo.setImageResource(img);
        TextView title = view.findViewById(R.id.text);
        title.setText(status);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feda_pmt);
        initView();
        clickListenner();
    }

    private void initView() {
        mMLogo = (ImageView) findViewById(R.id.mLogo);
        mMDescription = (TextView) findViewById(R.id.mDescription);
        mMTransactionRef = (TextView) findViewById(R.id.mTransactionRef);
        mMAmmount = (TextView) findViewById(R.id.mAmmount);
        mOperator = (Spinner) findViewById(R.id.operator);
        mCountryCode = (EditText) findViewById(R.id.countryCode);
        mPhoneNumber = (EditText) findViewById(R.id.phoneNumber);
        mStartPaiement = (Button) findViewById(R.id.startPaiement);
        //mStartPaiement.setOnClickListener(this);
    }

    public void clickListenner() {
        mOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                mode = "" + adapterView.getItemAtPosition(position);
                mode = mode.toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mStartPaiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePaiementRequest = new MakePaiement();
                showProgressDialog("Payement en cours...");

                country = "bj";
                phone = mPhoneNumber.getText().toString();

                makePaiementRequest.setMode(mode);
                makePaiementRequest.setToken(transactionToken);
                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setNumber(phone);
                phoneNumber.setCountry(country);

                makePaiementRequest.setPhone_number(phoneNumber);

                Paiement(makePaiementRequest, mode);

            }
        });
    }

    private void setData() {

        if (getIntent() != null) {
            fedaApiKey = getIntent().getStringExtra(Constants.FEDA_SP_KEY);
            fedaPayTransaction = (Transaction) getIntent().getSerializableExtra(Constants.TRANSACTION_DATA);
            getPaiementToken();

        } else {
            // Nous devons envoyer un message d'erreur
        }
    }

    private void getPaiementToken() {
        showProgressDialog("Patientez...");
        fedaPay.getPaiementToken(fedaApiKey, this, fedaPayTransaction.getId());
    }

    private void Paiement(MakePaiement makePaiementRqst, String mod) {
        showDialog(this, R.drawable.load, R.string.trans_in_process, false, "");
        fedaPay.makePaiement(fedaApiKey, mod, this, makePaiementRqst);
    }

    public void showDialog(Context cont, int img, int status, boolean cancelable, final String backResult) {
        dialogBuilder = new AlertDialog.Builder(cont);
        final View view = LayoutInflater.from(cont).inflate(R.layout.di, null);
        dialogBuilder.setView(view);

        ImageView logo = view.findViewById(R.id.logo);
        logo.setImageResource(img);
        TextView title = view.findViewById(R.id.text);
        title.setText(status);

        if (cancelable) {
            view.findViewById(R.id.ok).setVisibility(VISIBLE);
        } else {
            view.findViewById(R.id.ok).setVisibility(View.GONE);
            Animation Rotate = AnimationUtils.loadAnimation(cont, R.anim.rotation);
            logo.startAnimation(Rotate);
        }


        dialog = dialogBuilder.create();
        dialog.setCancelable(false);
        dialog.show();

        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                backToApp(backResult);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setData();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startPaiement) {// TODO 19/07/30

        }
    }

    @Override
    public void onTransactionCreated(Transaction items) {

    }

    @Override
    public void onPaiementMaked(Transaction items) {
        hideProgressDialog();
        // Fin du processus et envoie du statut de la transac
        Toast.makeText(this, "transaction" + items.getStatus(), Toast.LENGTH_LONG).show();
        GetTransactionStatus getTransactionStatusRequesttion = new GetTransactionStatus();
        getTransactionStatusRequesttion.setMode(items.getMode());
        getTransactionStatusRequesttion.setToken(transactionToken);
        fedaPay.getTransactionStatus(fedaApiKey, this, getTransactionStatusRequesttion, mode);

    }

    @Override
    public void onGetTransactionStatus(Transaction items) {
        Toast.makeText(this, "transaction " + items.getStatus(), Toast.LENGTH_LONG).show();
        dialog.cancel();
        if (items.getStatus().equals(FEDA_TRANSACTION_PENDDING)) {
            showDialog(this, R.drawable.load, R.string.trans_in_process, false, "");
        }
        if (items.getStatus().equals(FEDA_TRANSACTION_SUCCESS)) {
            showDialog(this, R.drawable.ok, R.string.trans_ok, true, items.getStatus());
            backToApp(items.getStatus());
        }
        if (items.getStatus().equals(FEDA_TRANSACTION_CANCELED)) {

            showDialog(this, R.drawable.faild, R.string.trans_fail, true, items.getStatus());
        }
    }

    @Override
    public void onTokenCreated(TokenResponse items) {
        hideProgressDialog();
        transactionToken = items.getToken();
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onError(Constants.Errors errorCode, String error) {

    }

    @Override
    public void onTransactionError(Constants.Errors errorCode, String error) {
        dialog.cancel();
        showDialog(this, R.drawable.faild, R.string.trans_fail, false, "Error");
    }
}
