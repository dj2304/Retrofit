package com.example.guoxinyu20181229.adaper;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guoxinyu20181229.R;
import com.example.guoxinyu20181229.bean.ShopBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ShopBean.DataBean> mData;
    private Context mContext;

    public ShopAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    public void setmData(List<ShopBean.DataBean> datas){
        mData.clear();
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void addmData(List<ShopBean.DataBean> datas){
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xrecycle_item, viewGroup, false);
        ViewHolderShop holderShop = new ViewHolderShop(view);
        return holderShop;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolderShop holderShop = (ViewHolderShop) viewHolder;
        holderShop.title.setText(mData.get(i).getTitle());
        holderShop.price.setText("价格:￥"+mData.get(i).getPrice());
        String img = mData.get(i).getImages().split("\\|")[0].replace("https","http");
        Uri uri = Uri.parse(img);
        holderShop.simple.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolderShop extends RecyclerView.ViewHolder {
        @BindView(R.id.simple)
        SimpleDraweeView simple;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.price)
        TextView price;
        public ViewHolderShop(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
