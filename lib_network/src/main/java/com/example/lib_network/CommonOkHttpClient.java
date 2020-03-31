package com.example.lib_network;

import com.example.lib_network.cookie.SimpleCookieJar;
import com.example.lib_network.https.HttpsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
        okBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        okBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder().addHeader("User-Agent", "Imooc-Mobile").build();
                return chain.proceed(request);
            }
        });


        okBuilder.cookieJar(new SimpleCookieJar());
        okBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okBuilder.followRedirects(true);
        okBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());
        mOkHttpClient = okBuilder.build();
    }


    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }




}
