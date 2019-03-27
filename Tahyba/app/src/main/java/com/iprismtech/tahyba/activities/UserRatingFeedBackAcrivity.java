package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.request.UserAddratingReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class UserRatingFeedBackAcrivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {

    private RatingBar rating_user;
    private EditText et_user_review;
    private String shop_ID;
    private Object obj;
    private TextView tv_review_submit;
    private ImageView iv_left_arrow;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_review_submit:
                if (et_user_review.getText().toString().isEmpty()) {
                    Toast.makeText(UserRatingFeedBackAcrivity.this, "Please write comment", Toast.LENGTH_SHORT).show();
                } else {
                    UserAddratingReq req = new UserAddratingReq();
                    req.userID = SharedPrefsUtils.getInstance(UserRatingFeedBackAcrivity.this).getId();
                    req.shopID = shop_ID;
                    req.revMsg = et_user_review.getText().toString();
                    req.rating = rating_user.getNumStars();

//                        req.password = et_enterpass.getText().toString();
//                        req.userId = userID;

                    try {
                        obj = Class.forName(UserAddratingReq.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "Ws/shop_add_review", true);

                }
                break;
            case R.id.iv_left_arrow:
                onBackPressed();
                finish();
                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_ratethis, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_review_submit.setOnClickListener(this);
        iv_left_arrow.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            shop_ID = bundle.getString("Key_ShopID");
        }

        rating_user = findViewById(R.id.rating_user);
        et_user_review = findViewById(R.id.et_user_review);
        tv_review_submit = findViewById(R.id.tv_review_submit);
        iv_left_arrow = findViewById(R.id.iv_left_arrow);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(UserRatingFeedBackAcrivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Toast.makeText(UserRatingFeedBackAcrivity.this, "Thank you for valuable Review and Rating", Toast.LENGTH_SHORT).show();
                            onBackPressed();
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(UserRatingFeedBackAcrivity.this, jsonObject.optString("message"));
                    onBackPressed();
                    finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
