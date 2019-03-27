package com.iprismtech.tahyba.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.base.BaseAbstractActivity;

import java.util.List;

public class ContactUsActivity extends BaseAbstractActivity<Class> implements View.OnClickListener {
    private ImageView toolback_contact;
    private LinearLayout call_me_lin, msg_me_lin;
    private TextView contct_num_tv, cont_email_tv;

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_contact_us, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        toolback_contact.setOnClickListener(this);
        call_me_lin.setOnClickListener(this);
        msg_me_lin.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        toolback_contact = findViewById(R.id.toolback_contact);
        call_me_lin = findViewById(R.id.call_me_lin);
        msg_me_lin = findViewById(R.id.msg_me_lin);
        contct_num_tv = findViewById(R.id.contct_num_tv);
        cont_email_tv = findViewById(R.id.cont_email_tv);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolback_contact:
                onBackPressed();
                break;
            case R.id.call_me_lin:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + contct_num_tv.getText().toString()));
                startActivity(intent);
                break;
            case R.id.msg_me_lin:
                /*emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", str_email, ""));
               // startActivity(Intent.createChooser(emailIntent, "Send email..."), 101);
                startActivityForResult(emailIntent,1);*/
                // final Intent intentemail = new Intent(android.content.Intent.ACTION_SEND);
                final Intent intentemail = new Intent(android.content.Intent.ACTION_SEND);

                //  Toast.makeText(getContext(),str_email,Toast.LENGTH_LONG).show();
                intentemail.setType("text/plain");
                intentemail.putExtra(Intent.EXTRA_EMAIL, cont_email_tv.getText().toString());
                intentemail.putExtra(Intent.EXTRA_SUBJECT, "Query");
                final PackageManager pm = getPackageManager();
                final List<ResolveInfo> matches = pm.queryIntentActivities(intentemail, 0);
                ResolveInfo best = null;
                for (final ResolveInfo info : matches)
                    if (info.activityInfo.packageName.endsWith(".gm") ||
                            info.activityInfo.name.toLowerCase().contains("gmail")) best = info;
                if (best != null)
                    intentemail.setClassName(best.activityInfo.packageName, best.activityInfo.name);
                startActivity(intentemail);

                break;
        }
    }
}

