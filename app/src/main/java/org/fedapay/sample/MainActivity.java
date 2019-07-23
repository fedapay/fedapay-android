package org.fedapay.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.fedapay.fedalib.FedaPay;
import org.fedapay.fedalib.PaiementDialog;

public class MainActivity extends AppCompatActivity {
    public FedaPay fedaPay;
    private Button mPayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fedaPay = new FedaPay(this);
        //fedaPay.welcome(this, "Bienvenue Chez Feda PAY");
        //fedaPay.startPaiement(this);

        mPayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void initView() {
        mPayer = (Button) findViewById(R.id.btnPayer);
    }

    private void openDialog() {

        PaiementDialog.display(getSupportFragmentManager());
    }

}
