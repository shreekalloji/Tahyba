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
import com.iprismtech.tahyba.request.OTPGenerationReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;

import org.json.JSONObject;

public class EnterMobileNumberActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private EditText forgotNumber;
    private TextView forgotPass_next_btn;
    private ImageView back_navigation;
    private Object obj;
    private OTPgenerationPojo otPgenerationPojo;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_mobile_enter, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        forgotPass_next_btn.setOnClickListener(this);
        back_navigation.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        forgotNumber = findViewById(R.id.forgotNumber);
        forgotPass_next_btn = findViewById(R.id.forgotPass_next_btn);
        back_navigation = findViewById(R.id.back_navigation);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(EnterMobileNumberActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            //  Common.showToast(EnterMobileNumberActivity.this, jsonObject.optString("message"));
                            otPgenerationPojo = gson.fromJson(jsonString, OTPgenerationPojo.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("Key_otp", String.valueOf(otPgenerationPojo.getOTP().getOtp()));
                            bundle.putString("Key_userId", String.valueOf(otPgenerationPojo.getUser_data().getAutho_unic_id()));
                            bundle.putString("Key_Mobile", String.valueOf(otPgenerationPojo.getUser_data().getImage()));


                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_FORGOT_PASSWORD_OTP_VERIFICATIONSCREEN, bundle);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(EnterMobileNumberActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forgotPass_next_btn:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (forgotNumber.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Enter Registered number", Toast.LENGTH_LONG).show();
                } else if (forgotNumber.getText().toString().length() != 10) {
                    Toast.makeText(context, "Enter 10 digit Registered number", Toast.LENGTH_LONG).show();
                } else {
                    OTPGenerationReq otpGenerationReq = new OTPGenerationReq();
                    otpGenerationReq.mobile = forgotNumber.getText().toString();
                    try {
                        obj = Class.forName(OTPGenerationReq.class.getName()).cast(otpGenerationReq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "Ws/resend_otp", true);

                }


                break;
            case R.id.back_navigation:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                finish();
                break;

        }
    }
}
