package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.adapters.ShopsListAdapter;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.ShopsListPojo;
import com.iprismtech.tahyba.request.MaincategoriesReq;
import com.iprismtech.tahyba.request.ShopsListReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.Constants;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;
import com.iprismtech.tahyba.utilities.Variables;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class ShopsListActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private Object obj;
    private ImageView iv_back, iv_banner_icon, iv_small_icon;
    private RecyclerView rview_list_shops;
    private LinearLayoutManager manager;
    private String categoryId, small_icon, banner_icon;
    private ShopsListAdapter shopsListAdapter;
    private ShopsListPojo shopsListPojo;
    private LinearLayout ll_back_shopslist;
    private String lat, lng;


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_back_shopslist:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                finish();
                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_shops_list_category, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        ll_back_shopslist.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        //  iv_back = findViewById(R.id.iv_back);
        rview_list_shops = findViewById(R.id.rview_list_shops);
        ll_back_shopslist = findViewById(R.id.ll_back_shopslist);


        rview_list_shops = findViewById(R.id.rview_list_shops);
        iv_banner_icon = findViewById(R.id.iv_banner_icon);
        iv_small_icon = findViewById(R.id.iv_small_icon);


        manager = new LinearLayoutManager(ShopsListActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_list_shops.setLayoutManager(manager);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            categoryId = getIntent().getExtras().getString("Key_shopId", "");
            small_icon = getIntent().getExtras().getString("Key_small_icon", "");
            banner_icon = getIntent().getExtras().getString("Key_Banner_icon", "");


            Picasso.with(ShopsListActivity.this)
                    .load(Constants.BASE_IMAGE_URL + small_icon)
                    .error(R.drawable.dummy)
                    .into(iv_small_icon);

            Picasso.with(ShopsListActivity.this)
                    .load(Constants.BASE_IMAGE_URL + banner_icon)
                    .error(R.drawable.dummy)
                    .into(iv_banner_icon);
        }

        lat = SharedPrefsUtils.getInstance(ShopsListActivity.this).getLat();
        lng = SharedPrefsUtils.getInstance(ShopsListActivity.this).getLng();

        ShopsListReq req = new ShopsListReq();
        req.userID = SharedPrefsUtils.getInstance(ShopsListActivity.this).getId();
        req.categoryID = categoryId;
        if (Variables.location_status == 0) {
            if (lat == null || lat.isEmpty() || lng == null || lng.isEmpty()) {
                req.userLat = "100";
                req.userLong = "100";
            } else {
                req.userLat = lat;
                req.userLong = lng;
            }
        } else if (Variables.location_status == 1) {

            req.userLat = Variables.cust_lat;
            req.userLong = Variables.cust_lng;
        }

//                        req.password = et_enterpass.getText().toString();
//                        req.userId = userID;

        try {
            obj = Class.forName(ShopsListReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/all_shops_list", true);
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ShopsListActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            shopsListPojo = gson.fromJson(jsonString, ShopsListPojo.class);
                            shopsListAdapter = new ShopsListAdapter(ShopsListActivity.this, shopsListPojo);
                            rview_list_shops.setAdapter(shopsListAdapter);
                            shopsListAdapter.setOnItemClickListener(new ShopsListAdapter.OnitemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (view.getId()) {
                                        case R.id.cv_item_shop_list:
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Key_ShopId", shopsListPojo.getResponse().get(position).getShop_id());
                                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SHOP_DETAILS_SCREEN, bundle);
                                            break;
                                    }
                                }
                            });

                            break;
                    }
                } else {
                    Common.showToast(ShopsListActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
        finish();
    }
}
