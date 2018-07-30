package com.coderpig.drysisters;

import android.app.Application;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

public class DryInit {

    private static Long HTTP_CONNECT_TIMEOUT = 30L;

    private static Long HTTP_WRITE_TIMEOUT = 30L;

    private static Long HTTP_READ_TIMEOUT = 30L;

    public static OkHttpClient mOkHttpClient;

    /**
     * 初始化okHttpClient
     */
    static void initOKHttp(Application app) {
        /**
         * Timber是一个轻量级的第三方库，能够帮助开发者更好的使用Android Log。
         * tag("okHttps")设置下一个日志调用时使用的一次性标签。
         * d:debug(message)
         * HttpLoggingInterceptor 是一个拦截器
         */
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                message -> Timber.tag("okHttps").d(message)
        );

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

    static void initTimber(){
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
