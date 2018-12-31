package com.example.guoxinyu20181229;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.Toast;

import com.example.guoxinyu20181229.adaper.ShopAdaper;
import com.example.guoxinyu20181229.api.Apis;
import com.example.guoxinyu20181229.bean.ShopBean;
import com.example.guoxinyu20181229.presents.PresenterImpl;
import com.example.guoxinyu20181229.view.Iview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Iview {

    @BindView(R.id.xrecycle)
    XRecyclerView xrecycle;
    private ShopAdaper adaper;
    private int mPage;
    private PresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl(this);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mPage = 1;
        xrecycle.setLoadingMoreEnabled(true);
        xrecycle.setPullRefreshEnabled(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xrecycle.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adaper = new ShopAdaper(this);
        xrecycle.setAdapter(adaper);
        xrecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        initData();
    }

    private void initData() {
        Map<String,String> map = new HashMap<>();
        map.put("page",String.valueOf(mPage));
        map.put("pscid","1");
        presenter.startRequest(Apis.URL_SHOP,map,ShopBean.class);
    }

    @Override
    public void requestData(Object o) {
        if(o instanceof ShopBean){
            ShopBean bean = (ShopBean) o;
            if(bean == null || !bean.isSuccess()){
                Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                if(mPage == 1){
                    adaper.setmData(bean.getData());
                }else{
                    adaper.addmData(bean.getData());
                }
                mPage++;
                xrecycle.loadMoreComplete();
                xrecycle.refreshComplete();
            }
        }
    }

    @Override
    public void requestFail(Object o) {
        if (o instanceof Exception) {
            Exception e = (Exception) o;
            e.printStackTrace();
        }
        Toast.makeText(MainActivity.this,"网络请求错误",Toast.LENGTH_SHORT).show();
    }
}
