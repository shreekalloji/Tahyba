package com.iprismtech.tahyba.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.support.design.widget.TabLayout;
import com.google.gson.Gson;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.adapters.MainBannerImagesAdapter;
import com.iprismtech.tahyba.base.BaseAbstractActivity;
import com.iprismtech.tahyba.fragments.OverviewFragment;
import com.iprismtech.tahyba.fragments.ReviewsRatingsFragment;
import com.iprismtech.tahyba.pojo.ShopOverviewPojo;
import com.iprismtech.tahyba.request.ShopOverviewReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShopDetailsActivity extends BaseAbstractActivity<Class> implements RetrofitResponseListener, View.OnClickListener {
    private ViewPager viewPager, viewpager_dots;
    private TabLayout tabLayout;
    private String shopId;
    private ShopOverviewPojo shopOverviewPojo;
    private OverviewFragment fragOverviewobj;
    private ReviewsRatingsFragment fragRatingsobj;
    private Object obj;
    private CirclePageIndicator indicator;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private LinearLayout ll_back_shopsdetails;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_shop_details, null);
        return view;
    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        viewPager = findViewById(R.id.viewpager);
        viewpager_dots = findViewById(R.id.viewpager_dots);
        tabLayout = findViewById(R.id.tabLayout);
        ll_back_shopsdetails = findViewById(R.id.ll_back_shopsdetails);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            shopId = bundle.getString("Key_ShopId");
        }
        ll_back_shopsdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

        Bundle bundle1 = new Bundle();
        bundle1.putString("Shop_ID", shopId);
// set Fragmentclass Arguments

        fragOverviewobj = new OverviewFragment();
        fragOverviewobj.setArguments(bundle1);
        fragRatingsobj = new ReviewsRatingsFragment();
        fragRatingsobj.setArguments(bundle1);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        ShopOverviewReq req = new ShopOverviewReq();
        req.userID = SharedPrefsUtils.getInstance(ShopDetailsActivity.this).getId();
        req.shopID = shopId;
        try {
            obj = Class.forName(ShopOverviewReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/shop_overview", true);


    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(fragOverviewobj, "Overview");
        adapter.addFragment(fragRatingsobj, "Ratings");
//
//        adapter.addFragment(new Inside_Visitors_fragment(), "Inside");
//        adapter.addFragment(new WaitingVisitorFragment(), "Waiting");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                Log.d("Scrolled", "");
                try {
//                    getChildFragmentManager().beginTransaction().detach(adapter.mFragmentList.get(i)).attach(adapter.mFragmentList.get(i)).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageSelected(int i) {
                Log.d("Selected", "");
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                Log.d("", "ScrollState");
                try {
//                    getChildFragmentManager().beginTransaction().detach(adapter.mFragmentList.get(i)).attach(adapter.mFragmentList.get(i)).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {
        if (objectResponse == null || objectResponse.equals("")) {
            Common.showToast(ShopDetailsActivity.this, "Please Try Again");
        } else {
            try {
                Gson gson = new Gson();
                String jsonString = gson.toJson(objectResponse);
                JSONObject jsonObject = new JSONObject(jsonString);
                if (jsonObject.optBoolean("status")) {
                    switch (requestId) {
                        case 1:
                            shopOverviewPojo = gson.fromJson(jsonString, ShopOverviewPojo.class);
                            viewpager_dots.setAdapter(new MainBannerImagesAdapter(ShopDetailsActivity.this, shopOverviewPojo));
                            init();
                            break;
                    }
                } else {
                    Common.showToast(ShopDetailsActivity.this, jsonObject.optString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void init() {

        //  indicator.setViewPager(viewpager_dots);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        //  indicator.setRadius(4 * density);

        NUM_PAGES = shopOverviewPojo.getResponse().getShop_banners().size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewpager_dots.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 4000, 3000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
