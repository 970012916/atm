package com.coderpig.drysisters.net;

import com.coderpig.drysisters.DryInit;
import com.coderpig.drysisters.data.result.GankResult;

import io.reactivex.Flowable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class APIService {

    private static String BASE_URI = "http://coderpig.com";

    private static APIService instance;

    public APIS apis;

    public static APIService getInstance(){
        if (null == instance) {
            instance = new APIService();
        }
        return instance;
    }

    private APIService() {
        Retrofit storeResultAPI = new Retrofit.Builder().baseUrl(BASE_URI)
                .client(DryInit.mOkHttpClient)
                /**
                 * 注册CallAdapter
                 */
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                /**
                 * Add converter factory for serialization and deserialization of objects.
                 */
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        /**
         * 创建接口实现
         */
        apis = storeResultAPI.create(APIS.class);
    }

    public interface APIS {

        /**
         * Gank.io 获取图片
         * Flowable(可观察者)
         */

        Flowable<GankResult> fetchGankMZ(
                @Path("count") int count,
                @Path("page") int page
        );
    }
}
