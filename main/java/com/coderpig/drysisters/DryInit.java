package com.coderpig.drysisters;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

/**
 * 描述：完成一些基本构造
 *
 * @author CoderPig on 2018/02/14 10:36.
 */

public class DryInit {
    private static Long  HTTP_CONNECT_TIMEOUT = 10L;
    private static Long  HTTP_WRITE_TIMEOUT = 30L;
    private static Long  HTTP_READ_TIMEOUT = 30L;

    public static OkHttpClient mOkHttpClient;

    /* 初始化OkHttpClient */
    static void initOKHttp(Application app) {
        /**
         * Timber是一个轻量级的第三方库，能够帮助开发者更好的使用Android Log。
         * tag("okHttps")设置下一个日志调用时使用的一次性标签。
         * d:debug(message)
         * HttpLoggingInterceptor 是一个拦截器
         */
        //HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> Timber.tag("OkHttps").d(message));
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                Log.d("zcb","OkHttp====Message:"+message);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        /**
         * TimeUnit.SECONDS（5）线程等待五秒

         TimeUnit.MILLISECONDS(5000)线程等待五秒.

         两者的时间单位不一样。

         内部都是Thread.sleep实现。
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                /**
                 * 添加拦截器
                 */
                .addInterceptor(logging);
        mOkHttpClient = builder.build();
    }

    /* 初始化Timber */
    static void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level= HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("zcb","OkHttp====Message:"+message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
