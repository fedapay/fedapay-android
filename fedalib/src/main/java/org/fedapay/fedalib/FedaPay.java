package org.fedapay.fedalib;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.fedapay.fedalib.Listenners.OnRequestLoadListener;
import org.fedapay.fedalib.Models.Requests.GetTransactionStatus;
import org.fedapay.fedalib.Models.Requests.MakePaiement;
import org.fedapay.fedalib.Models.Requests.StartTransaction;
import org.fedapay.fedalib.Networking.NetworkUtil;

public class FedaPay extends RelativeLayout {


    private static int SPLASH_DISPLAY_LENGTH = 3000;
    private static String ANDROID_NAME_SPACE = "http://schemas.android.com/apk/res/android";
    LayoutInflater mInflater;
    Context context;
    String xmlWidth = "notSet";
    View holderView;


    public FedaPay(Context context) {
        super(context);
        this.context = context;
    }

    public static void welcome(Context c, String message) {

        Toast.makeText(c, message, Toast.LENGTH_LONG).show();


    }


    public static void SetTransationOkView(View view, int img, int status) {

        ImageView logo = view.findViewById(R.id.logo);
        logo.setImageResource(img);
        TextView title = view.findViewById(R.id.text);
        title.setText(status);

    }

    public static void showLocationDialog(Context cont, String token, String mode, OnRequestLoadListener listener, MakePaiement makePaiementRequest) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        final View view = LayoutInflater.from(cont).inflate(R.layout.di, null);
        builder.setView(view);

        Animation Rotate = AnimationUtils.loadAnimation(cont, R.anim.rotation);

        ImageView img = (ImageView) view.findViewById(R.id.logo);
        img.startAnimation(Rotate);

        FedaPay.makePaiement(token, mode, listener, makePaiementRequest);

        /*final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SetTransationOkView(view, R.drawable.ok, R.string.trans_ok);
                view.findViewById(R.id.ok).setVisibility(VISIBLE);

            }
        }, SPLASH_DISPLAY_LENGTH);*/

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


    }

    public static void createTransaction(String token, OnRequestLoadListener listener, StartTransaction startTransaction) {
        NetworkUtil.createFedApi(token).newTransaction(startTransaction).enqueue(NetworkUtil.newTransactionCallBack(listener));
    }

    public static void makePaiement(String token, String mode, OnRequestLoadListener listener, MakePaiement makePaiementRequest) {
        NetworkUtil.createFedApi(token).newPaiement(makePaiementRequest, mode).enqueue(NetworkUtil.paiementCallBack(listener));


    }

    public static void getPaiementToken(String token, OnRequestLoadListener listener, String transactionId) {
        NetworkUtil.createFedApi(token).getToken(transactionId).enqueue(NetworkUtil.getTokenCallBack(listener));
    }

    public static void getTransactionStatus(String token, OnRequestLoadListener listener, GetTransactionStatus getTransactionStatusRequest, String mode) {
        NetworkUtil.createFedApi(token).checkStatus(getTransactionStatusRequest, mode).enqueue(NetworkUtil.getStatus(listener));
    }
}
