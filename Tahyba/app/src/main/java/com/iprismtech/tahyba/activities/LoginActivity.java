package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.tahyba.MainActivity;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.LoginPojo;
import com.iprismtech.tahyba.request.LoginReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class LoginActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private EditText edtPhone, edtPassword;
    private Object obj;
    private SharedPrefsUtils utils;
    private ImageView imgLogin, imgshowPassword;
    private TextView txtForgotpassword, btnSignup;
    private LoginPojo loginPojo;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_login_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(LoginActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            loginPojo = gson.fromJson(jsonString, LoginPojo.class);
                            SharedPrefsUtils.getInstance(LoginActivity.this).createUserSession(loginPojo.getResponse().getAutho_unic_id(), loginPojo.getResponse().getMobile(), loginPojo.getResponse().getEmail(), loginPojo.getResponse().getFull_name());
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(LoginActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        imgLogin.setOnClickListener(this);
//        imgshowPassword.setOnClickListener(this);
        txtForgotpassword.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        utils = new SharedPrefsUtils(LoginActivity.this);
        edtPhone = findViewById(R.id.edt_loginMobile);
        edtPassword = findViewById(R.id.edt_loginPassword);
        imgLogin = findViewById(R.id.imgLogin);
        txtForgotpassword = findViewById(R.id.txtForgotpassword);
        btnSignup = findViewById(R.id.btnSignup);
        requestLocationPermission();



    }


    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgLogin:
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                if (edtPhone.getText().toString().length() == 0 && edtPassword.getText().toString().length() == 0) {
                    Common.showToast(LoginActivity.this, "Please enter all fields");
                } else if (edtPhone.getText().toString().length() < 10) {
                    Common.showToast(LoginActivity.this, "Phone must be 10 numbers");
                } else if (edtPassword.getText().toString().length() < 5) {
                    Common.showToast(LoginActivity.this, "Password must be 6 chars");
                } else {
                    LoginReq loginReq = new LoginReq();
                    loginReq.mobile = edtPhone.getText().toString();
                    loginReq.password = edtPassword.getText().toString();

                    try {
                        obj = Class.forName(LoginReq.class.getName()).cast(loginReq);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "Ws/login_mobile", true);

                }
                break;
            case R.id.btnSignup:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.USER_SIGNUP_SCREEN);
                break;
            case R.id.txtForgotpassword:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ENTER_MOBILE_NUMBER_SCREEN);
                break;
        }
    }
}
