package com.iprismtech.tahyba.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.activities.ShopsListActivity;
import com.iprismtech.tahyba.adapters.ShopReviewsAsdapter;
import com.iprismtech.tahyba.base.BaseAbstractFragment;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.pojo.ShopReviewsPojo;
import com.iprismtech.tahyba.request.ShopsReviewsReq;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitRequester;
import com.iprismtech.tahyba.retrofitnetwork.RetrofitResponseListener;
import com.iprismtech.tahyba.utilities.Common;
import com.iprismtech.tahyba.utilities.SharedPrefsUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReviewsRatingsFragment extends BaseAbstractFragment<Class> implements View.OnClickListener, RetrofitResponseListener {
    private Object obj;
    private TextView tv_give_rating, tv_view_all_reviews;
    private RecyclerView rview_reviews;
    private LinearLayoutManager manager;
    private ShopReviewsPojo shopReviewsPojo;
    private ShopReviewsAsdapter shopReviewsAsdapter;
    private String Shop_ID;
    List<ShopReviewsPojo.ResponseBean.ReviewsBean> reviewsBeans;
    int startFrom=1;
    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_give_rating:
                Bundle bundle = new Bundle();
                bundle.putString("Key_ShopID", Shop_ID);
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_USER_RATING_FEEDBACK_SCREEN, bundle);
                break;

            case R.id.tv_view_all_reviews:
                ShopsReviewsReq req = new ShopsReviewsReq();
                req.userID = SharedPrefsUtils.getInstance(getActivity()).getId();
                req.shopID = Shop_ID;
                startFrom=startFrom+1;
                req.startFrom = startFrom;
                try {
                    obj = Class.forName(ShopsReviewsReq.class.getName()).cast(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new RetrofitRequester(this).callPostServices(obj, 1, "Ws/shop_reviews", true);

                break;
        }
    }

    @Override
    protected View getFragmentView() {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shop_ratings, null);
        return view;
    }

    @Override
    public void setPresenter() {

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
                            shopReviewsPojo = gson.fromJson(jsonString, ShopReviewsPojo.class);
                            reviewsBeans.addAll(shopReviewsPojo.getResponse().getReviews());

                            shopReviewsAsdapter = new ShopReviewsAsdapter(getActivity(), reviewsBeans);
                            rview_reviews.setAdapter(shopReviewsAsdapter);
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

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        tv_give_rating.setOnClickListener(this);
        tv_view_all_reviews.setOnClickListener(this);
    }

    @Override
    protected void initialiseViews() {
        super.initialiseViews();

        reviewsBeans=new ArrayList<>();
        rview_reviews = view.findViewById(R.id.rview_reviews);
        tv_give_rating = view.findViewById(R.id.tv_give_rating);
        tv_view_all_reviews = view.findViewById(R.id.tv_view_all_reviews);

        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rview_reviews.setLayoutManager(manager);

        Shop_ID = getArguments().getString("Shop_ID");
        ShopsReviewsReq req = new ShopsReviewsReq();
        req.userID = SharedPrefsUtils.getInstance(getActivity()).getId();
        req.shopID = Shop_ID;
        req.startFrom = startFrom;
        try {
            obj = Class.forName(ShopsReviewsReq.class.getName()).cast(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new RetrofitRequester(this).callPostServices(obj, 1, "Ws/shop_reviews", true);

    }
}

