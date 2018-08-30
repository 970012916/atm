package com.coderpig.drysisters.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.coderpig.drysisters.R;
import com.coderpig.drysisters.data.dto.GankMeizi;
import com.coderpig.drysisters.ui.activity.PictureDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author CoderPig on 2018/02/14 12:15.
 */

public class GankMZAdapter extends RecyclerView.Adapter<GankMZAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<GankMeizi> mzs = new ArrayList<>();

    public GankMZAdapter(Context mContext, ArrayList<GankMeizi> mzs) {
        this.mContext = mContext;
        this.mzs = mzs;
    }

    public void addAll(List<GankMeizi> data) {
        mzs.clear();
        mzs.addAll(data);
        notifyDataSetChanged();
    }

    public void loadMore(List<GankMeizi> data) {
        mzs.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mz, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mzs.get(position));
    }

    @Override
    public int getItemCount() {
        return mzs.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_content;

        ViewHolder(View itemView) {
            super(itemView);
            img_content = itemView.findViewById(R.id.img_content);
        }

        void bind(GankMeizi data) {
            /**
             * Glide，一个被google所推荐的图片加载库，
             * Glide的一个完整的请求至少需要三个参数，代码如下：
             * 1、with(Context context) - 需要上下文，这里还可以使用 Activity、FragmentActivity、
             * android.support.v4.app.Fragment、android.app.Fragment 的对象。
             * 将 Activity/Fragment 对象作为参数的好处是，图片的加载会和 Activity/Fragment 的生命周期保持一致
             *
             * 2、load(String url) - 这里我们所使用的一个字符串形式的网络图片的 URL，
             *
             * 3、into(ImageView imageView) - 你需要显示图片的目标 ImageView
             */
            Glide.with(mContext)
                    .load(data.getUrl())
                    .apply(new RequestOptions()
                            .centerCrop())
                    .into(img_content);
            img_content.setOnClickListener(view -> {
                Intent intent = new Intent(mContext, PictureDetailActivity.class);
                intent.putExtra("pic_url", data.getUrl());
                mContext.startActivity(intent);
            });
        }
    }
}
