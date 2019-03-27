package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.request.ResetPasswordRequest;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;

import org.json.JSONObject;

public class ResetPasswordActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {


    private EditText et_enterpass, et_confirmpass;
    private TextView btn_passwordSave;
    private Object obj;
    private RetrofitResponseListener retrofitResponseListener;
    private String userID;
    private String sUserID;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.password_save_btn:

                String etr_pass = et_enterpass.getText().toString();
                String cnf_pass = et_confirmpass.getText().toString();
                Log.d("password_data", etr_pass + cnf_pass);
                if (et_enterpass.getText().toString().isEmpty() || et_enterpass.getText().toString() == "") {
                    //et_enterpass.setError("Please Enter Password");
                    Toast.makeText(context, "Please enter Password", Toast.LENGTH_LONG).show();
                } else if (et_enterpass.getText().toString().length() < 6) {
                    // et_enterpass.setError("Password must be min 6 letters");
                    Toast.makeText(context, "Password must be min 6 Chars", Toast.LENGTH_LONG).show();
                } else if (et_confirmpass.getText().toString().isEmpty() || et_confirmpass.getText().toString() == "") {
                    //et_confirmpass.setError("Please Confirm Password");
                    Toast.makeText(context, "Please enter Confirm Password", Toast.LENGTH_LONG).show();
                } else if (et_confirmpass.getText().toString().length() < 6) {
                    //et_confirmpass.setError("Password must be min 6 letters");
                    Toast.makeText(context, "Password must be min 6 Chars", Toast.LENGTH_LONG).show();

                } else if (etr_pass.isEmpty() && cnf_pass.isEmpty()) {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_LONG).show();
                } else if (!et_enterpass.getText().toString().equals(et_confirmpass.getText().toString())) {
                    Toast.makeText(ResetPasswordActivity.this, "Password Not matched", Toast.LENGTH_SHORT).show();
                } else if (et_enterpass.getText().toString().equals(et_confirmpass.getText().toString())) {


                    ResetPasswordRequest req = new ResetPasswordRequest();

                    req.password = et_enterpass.getText().toString();
                    req.userID = sUserID;
                    req.cpassword = et_confirmpass.getText().toString();


                    try {
                        obj = Class.forName(ResetPasswordRequest.class.getName()).cast(req);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new RetrofitRequester(this).callPostServices(obj, 1, "ws/user_reset_password", true);
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);


//                    Toast.makeText(ResetPasswordActivity.this, "Password updated Successfully ", Toast.LENGTH_SHORT).show();
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);

                }

                break;
        }
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.reset_password_layout, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        btn_passwordSave.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        et_enterpass = findViewById(R.id.enterpass_et);
        et_confirmpass = findViewById(R.id.confirmpass_et);
        btn_passwordSave = findViewById(R.id.password_save_btn);
        retrofitResponseListener = this;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            sUserID = bundle.getString("Key_userId", "");
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ResetPasswordActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Toast.makeText(activity, "Password Successfully Updated", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                            finish();
                            break;
                    }
                } else {
                    Common.showToast(ResetPasswordActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
