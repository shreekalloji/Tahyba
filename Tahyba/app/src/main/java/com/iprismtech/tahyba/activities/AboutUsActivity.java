package com.iprismtech.tahyba.activities;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;

public class AboutUsActivity extends BaseAbstractActivity<Class> {
    private ImageView toolback_about;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_aboutus, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        toolback_about = findViewById(R.id.toolback_about);
        toolback_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_MAIN_SCREEN);
        finish();

    }
}
