package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.request.AppFeedBackReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class AppFeedBackActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener {
    private EditText et_user_app_feedback;
    private TextView tv_send_feedback;
    private Object obj;
    private ImageView iv_left_arrow;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_feedback, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        et_user_app_feedback = findViewById(R.id.et_user_app_feedback);
        tv_send_feedback = findViewById(R.id.tv_send_feedback);
        iv_left_arrow = findViewById(R.id.iv_left_arrow);
        tv_send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_user_app_feedback.getText().toString().isEmpty()) {
                    Toast.makeText(AppFeedBackActivity.this, "Please give issues and ideas", Toast.LENGTH_SHORT).show();
                } else {
                    callFeedbackWS();

                }
            }
        });
        iv_left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void callFeedbackWS() {
        AppFeedBackReq req = new AppFeedBackReq();
        req.userID = SharedPrefsUtils.getInstance(AppFeedBackActivity.this).getId();
        req.feedMsg = et_user_app_feedback.getText().toString();

        try {
            obj = Class.forName(AppFeedBackReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/user_feedback", true);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(AppFeedBackActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            Toast.makeText(AppFeedBackActivity.this, "Thank you for valuable feedback", Toast.LENGTH_SHORT).show();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                            finish();
                            break;
                    }
                } else {

                    Common.showToast(AppFeedBackActivity.this, jsonObject.optString("message"));
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
                    finish();
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
