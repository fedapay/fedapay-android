package org.magnific.fedapay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.magnific.fedapay.Basics.BaseActivity;
import org.magnific.fedapaylib.Mod.FedaPay;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView mMount;
    private Button mPayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mMount = (TextView) findViewById(R.id.mount);
        mPayer = (Button) findViewById(R.id.payer);
        mPayer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payer:
                // TODO 19/07/18

                openAcitivty(PaiementActivity.class);
                break;
            default:
                break;
        }
    }
}
