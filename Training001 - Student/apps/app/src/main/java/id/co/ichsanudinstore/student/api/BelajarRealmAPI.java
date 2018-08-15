package id.co.ichsanudinstore.student.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.GsonBuilder;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import id.co.ichsanudinstore.student.config.Constant;
import id.co.ichsanudinstore.student.utils.SharedPrefs;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BelajarRealmAPI {
    private static final transient String TAG = BelajarRealmAPI.class.getSimpleName();

    private static final transient Boolean ALLOW_INSECURE = true;
    private static List<Cookie> COOKIES = new ArrayList<>();

    @SuppressLint("BadHostnameVerifier")
    private static HostnameVerifier getHostnameVerifier() {
        return (s, sslSession) -> true;
    }

    @SuppressLint("TrustAllX509TrustManager")
    private static X509TrustManager getX509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext mSSLContext = SSLContext.getInstance("TLS");
            mSSLContext.init(null, new TrustManager[]{BelajarRealmAPI.getX509TrustManager()}, null);
            return mSSLContext.getSocketFactory();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static OkHttpClient getOkHhttpClient() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();
        mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (BelajarRealmAPI.ALLOW_INSECURE) {
            mBuilder.sslSocketFactory(BelajarRealmAPI.getSSLSocketFactory(), BelajarRealmAPI.getX509TrustManager());
            mBuilder.hostnameVerifier(BelajarRealmAPI.getHostnameVerifier());
        }
        mBuilder.connectTimeout(30, TimeUnit.SECONDS);
        mBuilder.readTimeout(30, TimeUnit.SECONDS);
        mBuilder.addInterceptor(mHttpLoggingInterceptor);
        mBuilder.cookieJar(new CookieJar() {
            @Override
            public void saveFromResponse(@NonNull HttpUrl url, @NonNull List<Cookie> cookies) {
                BelajarRealmAPI.COOKIES = cookies;
            }

            @Override
            public List<Cookie> loadForRequest(@NonNull HttpUrl url) {
                return BelajarRealmAPI.COOKIES;
            }
        });

        return mBuilder.build();
    }

    public static Retrofit getRetrofit(Context context) {
            OkHttpClient mOkHttpClient = BelajarRealmAPI.getOkHhttpClient();

            return new Retrofit.Builder()
                    .baseUrl(SharedPrefs.getmPreferences(context)
                            .getString(Constant.URL.BASE, ""))
                    .client(mOkHttpClient)
                    .addConverterFactory(
                            GsonConverterFactory.create(
                                    new GsonBuilder()
                                            .setLenient()
                                            .create()
                        )
                )
                .build();
    }
}
