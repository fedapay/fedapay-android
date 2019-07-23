package org.fedapay.fedalib;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class PaiementDialog extends DialogFragment {

    public static final String TAG = "paiement";

    private Toolbar toolbar;
    View view;
    private Spinner mOperator;
    private EditText mCountryCode;
    private EditText mPhoneNumber;
    private Button mStartPaiement;
    Context context;

    public static PaiementDialog display(FragmentManager fragmentManager) {
        PaiementDialog paiementDialog = new PaiementDialog();
        paiementDialog.show(fragmentManager, TAG);
        return paiementDialog;
    }
    private  ProgressDialog progressDialog;



    protected  void showProgressDialog(String msg) {
        try {
            if (progressDialog != null && !progressDialog.isShowing()) {
                progressDialog.setMessage(msg);
                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected  void hideProgressDialog() {
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
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
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

    public void clickListenner(){
        mStartPaiement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog("Payement en cours...");
                //FedaPay.welcome(context, "Bienvenue Chez Feda PAY");
                FedaPay.showLocationDialog(context);


            }
        });
    }
}
