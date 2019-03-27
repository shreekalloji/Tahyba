package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
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

public class OTPVerficationForgotPasswordActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {


    private PinEntryEditText txt_pin_entry;
    private TextView verifyotp_btn, txtResendcode, tv_test_otp;
    private String otp = "";
    private String sOtp, sMobile;
    private String sBlood, sUserID;
    private ImageView toolback_otp;
    private TextView txtitle;
    private Object obj;
    private OTPgenerationPojo otPgenerationPojo;
    private CountDownTimer countDownTimer;


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.toolback_otp:
                onBackPressed();
                finish();
                break;
            case R.id.txtResendcode:
//                if (txtResendcode.getText().toString().equalsIgnoreCase("Resend OTP")) {
                OTPGenerationReq otpGenerationReq = new OTPGenerationReq();
                otpGenerationReq.mobile = sMobile;
                try {
                    obj = Class.forName(OTPGenerationReq.class.getName()).cast(otpGenerationReq);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "Ws/resend_otp", true);
//                }
                break;
            case R.id.verifyotp_btn:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (otp.length() == 0 || otp.equalsIgnoreCase("")) {
                    Common.showToast(OTPVerficationForgotPasswordActivity.this, "Please enter Otp.");
                } else if (!otp.equalsIgnoreCase(String.valueOf(sOtp))) {
                    Common.showToast(OTPVerficationForgotPasswordActivity.this, "Otp Not matched.");
                } else if (otp.equalsIgnoreCase(String.valueOf(sOtp))) {
                    //make service call here
                    Bundle bundle = new Bundle();
                    bundle.putString("Key_userId", sUserID);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_RESET_PASSWORD_SCREEN, bundle);
                }
                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_otp_verification, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        toolback_otp.setOnClickListener(this);
        verifyotp_btn.setOnClickListener(this);
        txtResendcode.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        txt_pin_entry = findViewById(R.id.txt_pin_entry);
        txtResendcode = findViewById(R.id.txtResendcode);
        verifyotp_btn = findViewById(R.id.verifyotp_btn);
        tv_test_otp = findViewById(R.id.tv_test_otp);
        //txtitle = findViewById(R.id.txtitle);
        //txtitle.setText("OTP Verification");
        toolback_otp = findViewById(R.id.toolback_otp);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sUserID = bundle.getString("Key_userId", "");
            sOtp = bundle.getString("Key_otp", "");
            sMobile = bundle.getString("Key_Mobile", "");
        }
        tv_test_otp.setText(sOtp + "");
        txt_pin_entry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
            @Override
            public void onPinEntered(CharSequence str) {
                otp = str.toString();
            }
        });

        countDownTimer = new CountDownTimer(20000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // txtResendcode.setClickable(false);
                long seconds = millisUntilFinished / 1000;
                txtResendcode.setText("Resend code in : " + String.valueOf(seconds) + "secs");
            }

            @Override
            public void onFinish() {
                //txtResendcode.setClickable(true);
                txtResendcode.setText("Didn't Receive code? Resend Again");

            }
            // adjust the milli seconds here
        }.start();

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(OTPVerficationForgotPasswordActivity.this, "Please Try Again");
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
                            sOtp = String.valueOf(otPgenerationPojo.getOTP().getOtp());
                            tv_test_otp.setText(sOtp + "");
                            break;
                    }
                } else {
                    Common.showToast(OTPVerficationForgotPasswordActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
