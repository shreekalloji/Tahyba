package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.OTPgenerationPojo;
import com.iprismtech.tahyba.request.ForgotPasswordRequest;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;

import org.json.JSONObject;

public class ForgotPasswordActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private EditText et_mobile_number;
    private TextView tv_btn_next;
    private Object obj;
    private ImageView imgClose;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                onBackPressed();
                finish();
                break;
            case R.id.tv_btn_next:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (et_mobile_number.getText().toString().isEmpty()) {
                    Toast.makeText(ForgotPasswordActivity.this, "Please enter Mobile number", Toast.LENGTH_SHORT).show();
                } else if (et_mobile_number.getText().length() < 10) {
                    Toast.makeText(ForgotPasswordActivity.this, "Mobile Number must be 10 digits", Toast.LENGTH_SHORT).show();
                } else {
                    ForgotPasswordRequest req = new ForgotPasswordRequest();
                    req.mobile = et_mobile_number.getText().toString();

                    try {
                        obj = Class.forName(ForgotPasswordRequest.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "Ws/resend_otp", true);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                }
                break;
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_btn_next.setOnClickListener(this);

        imgClose.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        et_mobile_number = findViewById(R.id.et_mobile_number);
        tv_btn_next = findViewById(R.id.tv_btn_next);
        imgClose = findViewById(R.id.iv_left_arrow);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_forgot_password, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ForgotPasswordActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            OTPgenerationPojo forgotPasswordPojo = gson.fromJson(jsonString, OTPgenerationPojo.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Key_otp", String.valueOf(forgotPasswordPojo.getOTP().getOtp()));
                            bundle.putString("Key_userId", forgotPasswordPojo.getUser_data().getId());
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FORGOT_PASSWORD_OTP_VERIFICATIONSCREEN, bundle);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(ForgotPasswordActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
