package org.fedapay.fedalib;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FedaPay extends RelativeLayout{


    private static int SPLASH_DISPLAY_LENGTH = 3000;
    LayoutInflater mInflater;
    Context context;
    private static String ANDROID_NAME_SPACE = "http://schemas.android.com/apk/res/android";
    String xmlWidth = "notSet";
    View holderView;


    public FedaPay(Context context) {
        super(context);
        this.context = context;
    }

    public static void welcome(Context c, String message){

        Toast.makeText(c,message,Toast.LENGTH_LONG).show();


    }


    public static void SetTransationOkView(View view, int img, int status){

        ImageView logo = view.findViewById(R.id.logo);
        logo.setImageResource(img);
        TextView title = view.findViewById(R.id.text);
        title.setText(status);

    }

    public static void showLocationDialog(Context cont) {
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        final View view = LayoutInflater.from(cont).inflate(R.layout.di, null);
        builder.setView(view);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SetTransationOkView(view, R.drawable.ok, R.string.trans_ok);
                view.findViewById(R.id.ok).setVisibility(VISIBLE);

            }
        }, SPLASH_DISPLAY_LENGTH);

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }
}
