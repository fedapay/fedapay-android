package org.fedapay.fedalib;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.fedapay.fedalib.Listenners.OnRequestLoadListener;
import org.fedapay.fedalib.Models.PhoneNumber;
import org.fedapay.fedalib.Models.Requests.MakePaiement;
import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Transaction;
import org.fedapay.fedalib.Utils.Constants;

import static android.view.View.VISIBLE;
import static org.fedapay.fedalib.FedaPay.SetTransationOkView;


public class PaiementDialog extends DialogFragment implements OnRequestLoadListener {

    public static final String TAG = "paiement";
    static String fedaApiKey;
    static Transaction fedaPayTransaction;
    View view;
    Context context;
    FedaPay fedaPay;
    MakePaiement makePaiementRequest;
    String mode, phone, country, transactionToken;
    private Toolbar toolbar;
    private Spinner mOperator;
    private EditText mCountryCode;
    private EditText mPhoneNumber;
    private Button mStartPaiement;
    private ProgressDialog progressDialog;

    public static PaiementDialog display(FragmentManager fragmentManager, String token, Transaction transaction) {
        PaiementDialog paiementDialog = new PaiementDialog();
        paiementDialog.show(fragmentManager, TAG);
        fedaApiKey = token;
        fedaPayTransaction = transaction;
        return paiementDialog;
    }

    protected void showProgressDialog(String msg) {
        try {
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.setMessage(msg);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        context = getContext();
        fedaPay = new FedaPay(context);
        getPaiementToken();

        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    private void getPaiementToken() {
        showProgressDialog("Patientez...");
        fedaPay.getPaiementToken(fedaApiKey, this, fedaPayTransaction.getId());
    }

    private void Paiement(MakePaiement makePaiementRqst, String md) {
        FedaPay.showLocationDialog(context, fedaApiKey, md, this, makePaiementRqst);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.paiement, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        initView(view);

        return view;
    }

    private void initView(@NonNull final View itemView) {
        mOperator = (Spinner) itemView.findViewById(R.id.operator);
        mCountryCode = (EditText) itemView.findViewById(R.id.countryCode);
        mPhoneNumber = (EditText) itemView.findViewById(R.id.phoneNumber);
        mStartPaiement = (Button) itemView.findViewById(R.id.startPaiement);
    }


    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaiementDialog.this.dismiss();
            }
        });
        toolbar.setTitle("PAIEMENT");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        //toolbar.inflateMenu(R.menu.paiement);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                PaiementDialog.this.dismiss();
                return true;
            }
        });

        clickListenner();
    }

    public void clickListenner() {
        mOperator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                mode = "" + adapterView.getItemAtPosition(position);
                mode = mode.toLowerCase();
                //Toast.makeText(adapterView.getContext(), "OnItemSelectedListener : " + mode , Toast.LENGTH_LONG).show();
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
                //FedaPay.welcome(context, "Bienvenue Chez Feda PAY");

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

    @Override
    public void onTransactionCreated(Transaction items) {

    }

    @Override
    public void onPaiementMaked(Transaction items) {

        final View v = LayoutInflater.from(context).inflate(R.layout.di, null);
        SetTransationOkView(v, R.drawable.ok, R.string.trans_ok);
        v.findViewById(R.id.ok).setVisibility(VISIBLE);
    }

    @Override
    public void onGetTransactionStatus(Transaction items) {

    }

    @Override
    public void onTokenCreated(TokenResponse items) {

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
        final View v = LayoutInflater.from(context).inflate(R.layout.di, null);
        SetTransationOkView(v, R.drawable.faild, R.string.trans_fail);
        v.findViewById(R.id.ok).setVisibility(VISIBLE);
    }
}
