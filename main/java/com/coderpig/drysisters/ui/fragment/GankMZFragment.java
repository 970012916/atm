package com.coderpig.drysisters.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.coderpig.drysisters.R;
import com.coderpig.drysisters.ResUtils;
import com.coderpig.drysisters.data.dto.GankMeizi;
import com.coderpig.drysisters.net.APIService;
import com.coderpig.drysisters.ui.adapter.GankMZAdapter;
import com.coderpig.drysisters.utils.RxSchedulers;
import com.coderpig.drysisters.utils.ToastUtils;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * 描述：Gank.io妹子Fragment;
 */
public class GankMZFragment extends Fragment {

    private static final String TAG = "GankMZFragment";
    /**
     * 下拉刷新控件
     */
    private SwipeRefreshLayout srl_refresh;
    private FloatingActionButton fab_top;
    private RecyclerView rec_mz;
    private CompositeDisposable mSubscriptions;
    private GankMZAdapter mAdapter;
    private static final int PRELAOD_SIZE = 6;
    private  int mCurPage = 1;
    private ArrayList<GankMeizi> mData;
    /**
     * 设置动画速度
     */
    private final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();


    public static GankMZFragment newInstance(){
        return  new GankMZFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /**
         * 其中false意思为：把布局添加到父视图中，并保留父视图中的其他视图；
         */
        View view = inflater.inflate(R.layout.fragment_mz_content,container,false);
        srl_refresh = view.findViewById(R.id.srl_refresh);
        rec_mz = view.findViewById(R.id.rec_mz);
        fab_top = view.findViewById(R.id.fab_top);
        srl_refresh.setOnRefreshListener(() -> {
            mCurPage = 1;
            fetchGankMZ(true);
        });

        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        rec_mz.setLayoutManager(layoutManager);
        rec_mz.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if(layoutManager.getItemCount() - recyclerView.getChildCount() <= layoutManager.findFirstVisibleItemPosition()) {
                        mCurPage ++;
                        fetchGankMZ(false);
                    }
                    if(layoutManager.findLastCompletelyVisibleItemPosition() != 0) {

                        fabInAnim();
                    }else{
                        fabOutAnim();
                    }
                }
            }
        });
        fab_top.setOnClickListener( v -> {
                LinearLayoutManager manager = (LinearLayoutManager)rec_mz.getLayoutManager();
                //如果超过50项直接跳开头；
                if (manager.findFirstVisibleItemPosition() < 50 ) {
                    rec_mz.smoothScrollToPosition(0);
                } else {
                    rec_mz.scrollToPosition(0);
                    fabOutAnim();
                }
        });
        return view;
    }

    /**
     * onCreateView是创建的时候调用，onViewCreated是在onCreateView后被触发的事件，前后关系
     * 就是fragment中的onCreateView和onViewCreated的区别和联系。
     * 且onStart运行时间位于onViewCreated之后
     * @param view
     * @param savedInstanceState
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 这个compositeDisposable是fragment的全局变量，用于解除订阅关系。
         */
        mSubscriptions = new CompositeDisposable();
        mData = new ArrayList<>();
        mAdapter = new GankMZAdapter(getActivity(),mData);
        rec_mz.setAdapter(mAdapter);
        srl_refresh.setRefreshing(true);
        fetchGankMZ(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.clear();
    }

    /**
     * 拉取数据
     */
    private void fetchGankMZ(boolean isRefresh){
        Disposable subscribe = APIService.getInstance().apis.fetchGankMZ(20,mCurPage)
                /**
                 * subscribeOn(): 指定 subscribe() 所发生的线程，
                 * 即 Observable.OnSubscribe 被激活时所处的线程。
                 * 或者叫做事件产生的线程。
                 * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
                 */
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /**
                 * 而与 Subscriber.onStart() 相对应的，有一个方法 Observable.doOnSubscribe() 。
                 * 它和 Subscriber.onStart() 同样是在 subscribe() 调用后而且在事件发送前执行，
                 * 但区别在于它可以指定线程。默认情况下，
                 * doOnSubscribe() 执行在 subscribe() 发生的线程；
                 * 而如果在 doOnSubscribe() 之后有 subscribeOn() 的话，
                 * 它将执行在离它最近的 subscribeOn() 所指定的线程。
                 */
                .doOnSubscribe(subscription -> srl_refresh.setRefreshing(true))
                /**
                 * 在onError和或onCompleted后调用指定的操作，或由下游处理。
                 */
                .doFinally(() -> srl_refresh.setRefreshing(false))
                .subscribe(

                        /**
                         * data 的类型在 fetchGankMZ(20,mCurPage)方法的返回类型中确定 Flowable<GankResult> fetchGankMZ(）
                         */
                        data -> {
                            if (null == data && data.getResults() != null && data.getResults().size() > 0) {
                                ArrayList<GankMeizi> results = data.getResults();
                                if (isRefresh) {
                                    mAdapter.addAll(results);
                                    ToastUtils.shortToast("刷新成功");
                                } else {
                                    mAdapter.loadMore(results);
                                    /**
                                     * 下滑新增
                                     */
                                    String msg = String.format(ResUtils.getString(R.string.load_more_num), results.size(), "妹子");
                                    ToastUtils.shortToast(msg);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable e) throws Exception {
                                if(e instanceof ConnectException || e instanceof SocketException) {
                                    ToastUtils.shortToast(ResUtils.getString(R.string.network_connected_exception));
                                } else if(e instanceof SocketTimeoutException) {
                                    ToastUtils.shortToast(ResUtils.getString(R.string.network_socket_time_out));
                                } else if(e instanceof JsonSyntaxException) {
                                    ToastUtils.shortToast(ResUtils.getString(R.string.network_json_syntax_exception));
                                } else if(e instanceof UnknownHostException) {
                                    ToastUtils.shortToast(ResUtils.getString(R.string.network_unknown_host));
                                } else {
                                    Timber.d(e.getMessage());
                                }
                            }
                        });// RxSchedulers::processRequestException
                        //  其中Consumer中的accept()方法接收一个来自Observable的单个值。Consumer就是一个观察者。其他函数式接口可以类似应用。
        mSubscriptions.add(subscribe);
    }

    /* 悬浮按钮显示动画 */
    private void fabInAnim() {
        /**
         *  View.GONE : This view is invisible, and it doesn't take any space for layout
         */
        if (fab_top.getVisibility() == View.GONE){
            fab_top.setVisibility(View.VISIBLE);
            ViewCompat.animate(fab_top).scaleX(1.0f).scaleY(1.0f).alpha(1.0f)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null).start();
        }
    }

    /* 悬浮图标隐藏动画 */
    private void fabOutAnim() {
        if (fab_top.getVisibility() == View.VISIBLE) {
            ViewCompat.animate(fab_top).scaleX(0.0F).scaleY(0.0F).alpha(0.0F)
                    .setInterpolator(INTERPOLATOR).withLayer().setListener(new ViewPropertyAnimatorListener() {
                @Override
                public void onAnimationStart(View view) {

                }

                @Override
                public void onAnimationEnd(View view) {
                    fab_top.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(View view) {

                }
            }).start();
        }
    }

}
