package com.iprismtech.tahyba.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.adapters.HomecategoryAdapter;
import com.iprismtech.tahyba.adapters.Slidemenu_adapter;
import com.iprismtech.tahyba.base.BaseAbstractFragment;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.MainCategoriesPojo;
import com.iprismtech.tahyba.request.MaincategoriesReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.AlertUtils;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.GPSTracker;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;
import com.iprismtech.tahyba.utilities.Variables;

import org.json.JSONObject;


public class HomeFragment extends BaseAbstractFragment<Class> implements View.OnClickListener, RetrofitResponseListener {
    private String[] slide_menu_txt = {"Profile", "Share", "Feed Back Us", "Contact Us", "About Us", "Logout"};
    private int[] icons = {R.drawable.side_user_profile, R.drawable.side_share, R.drawable.side_feed_back, R.drawable.side_contact_us, R.drawable.side_about_us, R.drawable.ic_logout};
    private Slidemenu_adapter slidemenu_adapter;

    private ListView slidemenulistview;
    private DrawerLayout drawer_layout;
    private ImageView menu_icon, iv_notification;
    private HomecategoryAdapter homecategoryAdapter;
    private RecyclerView rview_home;
    private LinearLayoutManager manager;
    private LinearLayout ll_location_get, ll_search;
    private Object obj;
    private MainCategoriesPojo mainCategoriesPojo;
    private Double lat;
    private Double lng;
    private TextView tv_loc_city;
    private SharedPrefsUtils prefsUtils;
    private EditText et_search;

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_memu:
                if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    getActivity().getWindow().setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                } else {
                    drawer_layout.openDrawer(Gravity.LEFT);

                }
                break;
            case R.id.ll_location_get:

//                Intent intent = new Intent(getActivity(), CustomLocationActivity.class);
//                startActivityForResult(intent, 1);

                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOCATION_GET_SCREEN);

                break;
            case R.id.ll_search:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SHOPS_SEARCH_SCREEN);
                break;
            case R.id.et_search:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SHOPS_SEARCH_SCREEN);
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view;
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_screen, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        menu_icon.setOnClickListener(this);
        ll_location_get.setOnClickListener(this);
        ll_search.setOnClickListener(this);
        et_search.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        drawer_layout = view.findViewById(R.id.drawer_layout);
        ll_location_get = view.findViewById(R.id.ll_location_get);
        tv_loc_city = view.findViewById(R.id.tv_loc_city);
        ll_search = view.findViewById(R.id.ll_search);
        et_search = view.findViewById(R.id.et_search);
        menu_icon = view.findViewById(R.id.iv_memu);
        slidemenulistview = view.findViewById(R.id.slidemenulistview);
        prefsUtils = new SharedPrefsUtils(getActivity());
        rview_home = view.findViewById(R.id.rview_home);
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_home.setLayoutManager(manager);

        slidemenu_adapter = new Slidemenu_adapter(getActivity(), slide_menu_txt, icons);
        slidemenulistview.setAdapter(slidemenu_adapter);

        slidemenulistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0) {

                    drawer_layout.closeDrawer(Gravity.LEFT);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_PROFILE_ACTIVITY_SCREEN);

                } else if (i == 1) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    try {
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String sAux = "\nLet me recommend you this application\n\n";
                        sAux = sAux + "http://play.google.com/store/apps/details?id=" + getActivity().getPackageName();
                        intent.putExtra(Intent.EXTRA_TEXT, sAux);
                        startActivity(Intent.createChooser(intent, "choose one"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (i == 2) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_APP_FEEDBACK_US_SCREEN);

                } else if (i == 3) {
                    drawer_layout.closeDrawer(Gravity.LEFT);
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_CONTACT_US_SCREEN);
                } else if (i == 4) {
                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_ABOUT_US_SCREEN);
                    drawer_layout.closeDrawer(Gravity.LEFT);
                } else if (i == 5) {


                    AlertUtils.showSimpleAlert(getActivity(), "Do you want to Logout from the Application", "Confirm...?", "Yes", "No", new AlertUtils.onClicklistners() {
                        @Override
                        public void onpositiveclick() {
                            SharedPrefsUtils.logoutUser();
                            ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SPLASH_SCREEN);
                            getActivity().finish();
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });


                    drawer_layout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        if (Variables.location_status == 0) {

            getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    GPSTracker gpsTracker = new GPSTracker(getActivity());
                    lat = gpsTracker.getLatitude();
                    lng = gpsTracker.getLongitude();
                    prefsUtils.storelatlangcode(String.valueOf(lat), String.valueOf(lng));
                    String ddress = Common.getAddressString(getActivity(), lat, lng);
                    Variables.current_address = ddress;
                    tv_loc_city.setText(ddress);
                }
            });
        }
        MaincategoriesReq req = new MaincategoriesReq();
        req.userID = SharedPrefsUtils.getInstance(getActivity()).getId();
//                        req.password = et_enterpass.getText().toString();
//                        req.userId = userID;


        try {
            obj = Class.forName(MaincategoriesReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/get_all_categories", true);


        if (Variables.location_status == 1) {
            tv_loc_city.setText(Variables.cust_address);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                lat = data.getDoubleExtra("lat", 0.0);
                lng = data.getDoubleExtra("lng", 0.0);
                String addr = data.getStringExtra("addr");
                prefsUtils.storelatlangcode(String.valueOf(lat), String.valueOf(lng));
                tv_loc_city.setText(addr);
                Variables.location_status = 1;
                Variables.cust_lat = String.valueOf(lat);
                Variables.cust_lng = String.valueOf(lng);
                Variables.cust_lat = addr;
                // Toast.makeText(getActivity(), building_id + "and" + name, Toast.LENGTH_SHORT).show();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }

        }
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
                            mainCategoriesPojo = gson.fromJson(jsonString, MainCategoriesPojo.class);
                            homecategoryAdapter = new HomecategoryAdapter(getActivity(), mainCategoriesPojo);
                            rview_home.setAdapter(homecategoryAdapter);
                            homecategoryAdapter.setOnItemClickListener(new HomecategoryAdapter.OnitemClickListener() {
                                @SuppressLint("WrongConstant")
                                @Override
                                public void onItemClick(View view, int position) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("Key_shopId", mainCategoriesPojo.getResponse().get(position).getId());
                                    bundle.putString("Key_small_icon", mainCategoriesPojo.getResponse().get(position).getMobicon());
                                    bundle.putString("Key_Banner_icon", mainCategoriesPojo.getResponse().get(position).getSmmob_banner());
                                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SHOPS_LIST_SCREEN, bundle);
                                    getActivity().finish();
                                }
                            });

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
}
