package org.fedapay.sample.Uils;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;

import org.fedapay.fedalib.Models.Transaction;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        progressDialog = new ProgressDialog(this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setActionBar();

    }

    protected void setActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    protected void openAcitivty(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void openAcitivty(Intent intent2, Class<?> cls) {
        Intent intent = new Intent(this, cls);
        if (intent2 != null) {
            intent = intent2;
            intent.setClass(this, cls);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    protected void openAcitivtyForResult(Class<?> cls, Transaction transaction, int requestCode, String apiKey) {

        Intent intent = new Intent(this, cls);
        intent.putExtra("feda_sp_key", apiKey);
        intent.putExtra(Constants.TRANSACTION_DATA, transaction);
        startActivityForResult(intent,requestCode);
    }

    protected void openAcitivty(ArrayList<String> categories, Class<?> cls, int code, String title, String seletectedItem) {
        Intent newIntent = new Intent(this, cls);
        newIntent.putStringArrayListExtra(Constants.DATA, categories);
        newIntent.putExtra(Constants.TITLE, title);
        newIntent.putExtra(Constants.SELECTED_ITEM, seletectedItem);
        startActivityForResult(newIntent, code);
    }

    protected void openActivityForResult(Class<?> cls, Transaction transaction) {
        Intent intent = new Intent(this, cls);
        intent.putExtra(Constants.IS_RESULT_ACTIVITY, true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, Constants.REQUEST_KEY_PMT);
    }

}
