package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.OTPgenerationPojo;
import com.iprismtech.tahyba.request.OTPGenerationReq;
import com.iprismtech.tahyba.request.UserSignUpReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;

import org.json.JSONObject;

public class UserSignUpActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private EditText edtSignupName, edtSignupPhone, edtSignupEmail, edtSignupPassword, edtSignupCnfPassword;
    private ImageView imgSignup;
    private TextView txtLogin;
    private Object obj;
    private OTPgenerationPojo otPgenerationPojo;

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtLogin:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                break;
            case R.id.imgSignup:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (edtSignupName.getText().toString().length() == 0) {
                    Common.showToast(UserSignUpActivity.this, "Please Enter Name");
                } else if (edtSignupPhone.getText().toString().length() == 0 || edtSignupPhone.getText().toString().length() < 10) {
                    Common.showToast(UserSignUpActivity.this, "Please Enter Phone");
                } else if (edtSignupEmail.getText().toString().length() == 0 && !isValidEmail(edtSignupEmail.getText().toString())) {
                    Common.showToast(UserSignUpActivity.this, "Please Enter Email");
                } else if (edtSignupPassword.getText().toString().length() == 0) {
                    Common.showToast(UserSignUpActivity.this, "Please Enter Password");
                } else if (edtSignupCnfPassword.getText().toString().length() == 0) {
                    Common.showToast(UserSignUpActivity.this, "Please Enter Confirm Password");
                } else if (!edtSignupPassword.getText().toString().equalsIgnoreCase(edtSignupCnfPassword.getText().toString())) {
                    Common.showToast(UserSignUpActivity.this, "Make sure password and confirm password same");
                } else {
                    //call service here
                    OTPGenerationReq otpGenerationReq = new OTPGenerationReq();
                    otpGenerationReq.mobile = edtSignupPhone.getText().toString();
                    try {
                        obj = Class.forName(OTPGenerationReq.class.getName()).cast(otpGenerationReq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "Ws/resend_otp", true);
                }
                break;

        }
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_signup_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(UserSignUpActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:

                            Common.showToast(UserSignUpActivity.this, jsonObject.optString("message"));
                            otPgenerationPojo = gson.fromJson(jsonString, OTPgenerationPojo.class);
                            Bundle bundle = new Bundle();

                            bundle.putString("Key_Name", edtSignupName.getText().toString());
                            bundle.putString("Key_Mobile", edtSignupPhone.getText().toString());
                            bundle.putString("Key_Email", edtSignupEmail.getText().toString());
                            bundle.putString("Key_Password", edtSignupPassword.getText().toString());
                            bundle.putInt("Key_OTP", otPgenerationPojo.getOTP().getOtp());
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_OTPVERIFICATION_SCREEN, bundle);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(UserSignUpActivity.this, jsonObject.optString("message"));
                    View view = this.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgSignup.setOnClickListener(this);
        txtLogin.setOnClickListener(this);

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        edtSignupName = findViewById(R.id.edtSignupName);
        edtSignupPhone = findViewById(R.id.edtSignupPhone);
        edtSignupEmail = findViewById(R.id.edtSignupEmail);
        edtSignupPassword = findViewById(R.id.edtSignupPassword);
        edtSignupCnfPassword = findViewById(R.id.edtSignupCnfPassword);
        imgSignup = findViewById(R.id.imgSignup);
        txtLogin = findViewById(R.id.txtLogin);
    }
}
