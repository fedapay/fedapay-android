package org.fedapay.fedalib.Networking;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.fedapay.fedalib.BuildConfig;
import org.fedapay.fedalib.Listenners.OnRequestLoadListener;
import org.fedapay.fedalib.Models.Responses.TokenResponse;
import org.fedapay.fedalib.Models.Responses.TransactionResponse;
import org.fedapay.fedalib.Utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtil {

    private static Retrofit retrofit = null;
    private static int REQUEST_TIMEOUT = 60;
    private static OkHttpClient okHttpClient;
    private static Gson gson;

    public static FedApi createFedApi(final String accessToken) {

        if (okHttpClient == null)
            initOkHttp(accessToken);

        gson = new GsonBuilder().setLenient().create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit.create(FedApi.class);
    }


    private static void initOkHttp(final String accessToken) {
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(interceptor);

        // TODO - add no internet connection interceptor

        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json");
                if (!TextUtils.isEmpty(accessToken)) {
                    requestBuilder.addHeader("Authorization", "Bearer " + accessToken);
                }

                Request request = requestBuilder.build();
                return chain.proceed(request);

            }
        });


        okHttpClient = httpClient.build();
    }


    public static Callback<TransactionResponse> newTransactionCallBack(final OnRequestLoadListener listener) {

        return new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if (listener != null) {

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            listener.onTransactionCreated(response.body().getTransaction());
                        }
                    } else {
                        listener.onError(Constants.Errors.FAILED, "Fedapay error : " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                t.printStackTrace();
                //if(listener != null) {
                //  listener.onError(Constants.Errors.FAILED, t.getLocalizedMessage());
                //}
            }
        };

    }

    public static Callback<TokenResponse> getTokenCallBack(final OnRequestLoadListener listener) {

        return new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (listener != null) {

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            listener.onTokenCreated(response.body());
                        }
                    } else {
                        listener.onError(Constants.Errors.FAILED, "Fedapay error : " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                t.printStackTrace();
                //if(listener != null) {
                //  listener.onError(Constants.Errors.FAILED, t.getLocalizedMessage());
                //}
            }
        };

    }

    public static Callback<TransactionResponse> paiementCallBack(final OnRequestLoadListener listener) {

        return new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if (listener != null) {

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            listener.onPaiementMaked(response.body().getTransaction());
                        }
                    } else {
                        listener.onTransactionError(Constants.Errors.FAILED, "Fedapay error : " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                t.printStackTrace();
                //if(listener != null) {
                //  listener.onError(Constants.Errors.FAILED, t.getLocalizedMessage());
                //}
            }
        };

    }

    public static Callback<TransactionResponse> getStatus(final OnRequestLoadListener listener) {

        return new Callback<TransactionResponse>() {
            @Override
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                if (listener != null) {

                    if (response.isSuccessful()) {

                        if (response.body() != null) {

                            listener.onGetTransactionStatus(response.body().getTransaction());
                        }
                    } else {
                        listener.onTransactionError(Constants.Errors.FAILED, "Fedapay error : " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                t.printStackTrace();
                //if(listener != null) {
                //  listener.onError(Constants.Errors.FAILED, t.getLocalizedMessage());
                //}
            }
        };

    }
}