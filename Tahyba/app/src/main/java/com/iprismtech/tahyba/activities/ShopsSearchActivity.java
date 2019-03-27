package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.adapters.ShopSearchAdapter;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.ShopsSearchPojo;
import com.iprismtech.tahyba.request.ShopsSearchReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;
import com.iprismtech.tahyba.utilities.Variables;

import org.json.JSONObject;

public class ShopsSearchActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener {
    private EditText et_search;
    private Object obj;
    private TextView tv_location_city;
    private ShopsSearchPojo shopsSearchPojo;
    private RecyclerView rview_search_shops;
    private LinearLayoutManager manager;
    private ShopSearchAdapter shopSearchAdapter;
    private ImageView iv_back_arrow;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_shops_search, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ShopsSearchActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            shopsSearchPojo = gson.fromJson(jsonString, ShopsSearchPojo.class);
                            shopSearchAdapter = new ShopSearchAdapter(ShopsSearchActivity.this, shopsSearchPojo);
                            rview_search_shops.setAdapter(shopSearchAdapter);
                            shopSearchAdapter.setOnItemClickListener(new ShopSearchAdapter.OnitemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Key_ShopId", shopsSearchPojo.getResponse().get(position).getShop_id());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SHOP_DETAILS_SCREEN, bundle);
                                }
                            });
                            break;
                    }
                } else {
                    View view = this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    Common.showToast(ShopsSearchActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();


        rview_search_shops = findViewById(R.id.searchresults);
        iv_back_arrow = findViewById(R.id.iv_back_arrow);
        tv_location_city = findViewById(R.id.tv_location_city);
        manager = new LinearLayoutManager(ShopsSearchActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_search_shops.setLayoutManager(manager);

        if (Variables.location_status == 0) {
            tv_location_city.setText(Variables.current_address);
        } else {
            tv_location_city.setText(Variables.cust_address);
        }

        et_search = findViewById(R.id.et_search);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 3) {
                    callSearchWs(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        iv_back_arrow.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                finish();
            }
        });
    }

    private void callSearchWs(CharSequence s) {
        ShopsSearchReq req = new ShopsSearchReq();
        req.userID = SharedPrefsUtils.getInstance(ShopsSearchActivity.this).getId();
        req.userLat = SharedPrefsUtils.getInstance(ShopsSearchActivity.this).getLat();
        req.userLong = SharedPrefsUtils.getInstance(ShopsSearchActivity.this).getLng();
        req.keyWords = s.toString();

        try {
            obj = Class.forName(ShopsSearchReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/search_shops", true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
        finish();
    }
}
