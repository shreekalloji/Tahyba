package com.iprismtech.tahyba.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;

import com.iprismtech.tahyba.base.BaseAbstractFragment;
import com.iprismtech.tahyba.pojo.ShopOverviewPojo;
import com.iprismtech.tahyba.request.ShopOverviewReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.GPSTracker;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

import org.json.JSONObject;

public class OverviewFragment extends BaseAbstractFragment<Class> implements View.OnClickListener, RetrofitResponseListener {

    private ShopOverviewPojo shopOverviewPojo;
    private Object obj;
    private LinearLayout linear_call, linear_website, linear_direction;
    private TextView tv_shop_name_overview, tv_shop_address_overview, tv_rarting_overview, tv_discrption,
            tv_shop_timimgs, shop_working_days_sunday, shop_address, shop_working_days_monday, shop_working_days_thuesday, shop_working_days_wedday,
            shop_working_days_thursday, shop_working_days_friday, shop_working_days_satday;
    private ImageView naviagatemap;
    private RatingBar ratingbar_shop_overview;
    private String website, latitute, longitude, mobile_number;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_website:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://" + website));
                startActivity(i);
                break;
            case R.id.linear_direction:
                try {
                    GPSTracker gpsTracker = new GPSTracker(getActivity());
                    String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + "&daddr=" + latitute + "," + longitude;
                    Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(Intent.createChooser(intent1, "Select an application"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error Maps", e.toString());
                }
                break;
            case R.id.naviagatemap:
                try {
                    GPSTracker gpsTracker = new GPSTracker(getActivity());


                    String uri = "http://maps.google.com/maps?f=d&hl=en&saddr=" + gpsTracker.getLatitude() + "," + gpsTracker.getLongitude() + "&daddr=" + latitute + "," + longitude;
                    Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(Intent.createChooser(intent1, "Select an application"));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error Maps", e.toString());
                }
                break;
            case R.id.linear_call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile_number));
                startActivity(intent);
                break;
        }
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_overview, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        linear_call.setOnClickListener(this);
        linear_website.setOnClickListener(this);
        linear_direction.setOnClickListener(this);
        naviagatemap.setOnClickListener(this);

    }


    @Override
    protected void initialiseViews() {
        super.initialiseViews();
        linear_call = view.findViewById(R.id.linear_call);
        linear_website = view.findViewById(R.id.linear_website);
        linear_direction = view.findViewById(R.id.linear_direction);

        tv_shop_name_overview = view.findViewById(R.id.tv_shop_name_overview);
        tv_shop_address_overview = view.findViewById(R.id.tv_shop_address_overview);
        tv_rarting_overview = view.findViewById(R.id.tv_rarting_overview);
        tv_discrption = view.findViewById(R.id.tv_discrption);
        tv_shop_timimgs = view.findViewById(R.id.tv_shop_timimgs);
        shop_working_days_sunday = view.findViewById(R.id.shop_working_days_sunday);
        shop_working_days_monday = view.findViewById(R.id.shop_working_days_monday);
        shop_working_days_thuesday = view.findViewById(R.id.shop_working_days_thuesday);
        shop_working_days_wedday = view.findViewById(R.id.shop_working_days_wedday);
        shop_working_days_thursday = view.findViewById(R.id.shop_working_days_thursday);
        shop_working_days_friday = view.findViewById(R.id.shop_working_days_friday);
        shop_working_days_satday = view.findViewById(R.id.shop_working_days_satday);

        ratingbar_shop_overview = view.findViewById(R.id.ratingbar_shop_overview);
        shop_address = view.findViewById(R.id.shop_address);
        naviagatemap = view.findViewById(R.id.naviagatemap);

        String Shop_ID = getArguments().getString("Shop_ID");
        ShopOverviewReq req = new ShopOverviewReq();
        req.userID = SharedPrefsUtils.getInstance(getActivity()).getId();
        req.shopID = Shop_ID;
        try {
            obj = Class.forName(ShopOverviewReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/shop_overview", true);

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(getActivity(), "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            shopOverviewPojo = gson.fromJson(jsonString, ShopOverviewPojo.class);
                            tv_shop_name_overview.setText(shopOverviewPojo.getResponse().getShop_details().getShop_name());
                            tv_shop_address_overview.setText(shopOverviewPojo.getResponse().getShop_details().getAddress());
                            tv_rarting_overview.setText(shopOverviewPojo.getResponse().getShop_details().getAvgRate());
                            tv_discrption.setText(shopOverviewPojo.getResponse().getShop_details().getBusiness_information());
                            website = shopOverviewPojo.getResponse().getShop_details().getBusiness_site();
                            latitute = shopOverviewPojo.getResponse().getShop_details().getLatitude();
                            longitude = shopOverviewPojo.getResponse().getShop_details().getLongitude();
                            mobile_number = shopOverviewPojo.getResponse().getShop_details().getMobile();
                            if (shopOverviewPojo.getResponse().getShop_details().getOpen_status().equalsIgnoreCase("1")) {
                                tv_shop_timimgs.setText(" Open Now");
                            } else if
                                    (shopOverviewPojo.getResponse().getShop_details().getOpen_status() == null) {
                                tv_shop_timimgs.setText(" ");
                            } else {
                                tv_shop_timimgs.setText(" Closed Now");
                            }

//                            String days_result;
//                            for(int i=0;i<shopOverviewPojo.getResponse().getShop_hrs().size();i++){
//                                if(shopOverviewPojo.getResponse().getShop_hrs().get(i).getOpen_status())
//
//                            }
//                            shop_working_days.setText("Sunday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(0).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(0).getClose_hour() + '\n'
//                                    + "Monday  : " + shopOverviewPojo.getResponse().getShop_hrs().get(1).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(1).getClose_hour() + '\n'
//                                    + "Tuesday  : " + shopOverviewPojo.getResponse().getShop_hrs().get(2).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(2).getClose_hour() + '\n'
//                                    + "Wednesday: " + shopOverviewPojo.getResponse().getShop_hrs().get(3).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(3).getClose_hour() + '\n'
//                                    + "Thursday : " + shopOverviewPojo.getResponse().getShop_hrs().get(4).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(4).getClose_hour() + '\n'
//                                    + "Friday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(5).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(5).getClose_hour() + '\n'
//                                    + "Saturday : " + shopOverviewPojo.getResponse().getShop_hrs().get(6).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(6).getClose_hour() + '\n');
                            // shop_working_days

                            if (shopOverviewPojo.getResponse().getShop_hrs().get(0).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_sunday.setText("Sunday   : Closed");
                            } else {
                                shop_working_days_sunday.setText("Sunday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(0).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(0).getClose_hour());
                            }


                            if (shopOverviewPojo.getResponse().getShop_hrs().get(1).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_monday.setText("Monday   : Closed");
                            } else {
                                shop_working_days_monday.setText("Monday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(1).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(1).getClose_hour());
                            }

                            if (shopOverviewPojo.getResponse().getShop_hrs().get(2).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_thuesday.setText("Tuesday   : Closed");
                            } else {
                                shop_working_days_thuesday.setText("Tuesday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(2).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(2).getClose_hour());
                            }


                            if (shopOverviewPojo.getResponse().getShop_hrs().get(3).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_wedday.setText("Wednesday: Closed");
                            } else {
                                shop_working_days_wedday.setText("Wednesday: " + shopOverviewPojo.getResponse().getShop_hrs().get(3).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(3).getClose_hour());
                            }

                            if (shopOverviewPojo.getResponse().getShop_hrs().get(4).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_thursday.setText("Thursday   : Closed");
                            } else {
                                shop_working_days_thursday.setText("Thursday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(4).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(4).getClose_hour());
                            }


                            if (shopOverviewPojo.getResponse().getShop_hrs().get(5).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_friday.setText("Friday   : Closed");
                            } else {
                                shop_working_days_friday.setText("Friday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(5).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(5).getClose_hour());
                            }
                            if (shopOverviewPojo.getResponse().getShop_hrs().get(6).getOpen_status().equalsIgnoreCase("0")) {
                                shop_working_days_satday.setText("Saturday   : Closed");
                            } else {
                                shop_working_days_satday.setText("Saturday   : " + shopOverviewPojo.getResponse().getShop_hrs().get(6).getOpen_hour() + "-" + shopOverviewPojo.getResponse().getShop_hrs().get(6).getClose_hour());
                            }


                            shop_address.setText(shopOverviewPojo.getResponse().getShop_details().getAddress());
                            ratingbar_shop_overview.setRating(Float.parseFloat(shopOverviewPojo.getResponse().getShop_details().getAvgRate()));
                            break;
                    }
                } else {
                    Common.showToast(getActivity(), jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }
}
