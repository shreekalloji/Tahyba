package com.iprismtech.tahyba.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.activities.ShopDetailsActivity;
import com.iprismtech.tahyba.pojo.ShopOverviewPojo;
import com.iprismtech.tahyba.utilities.Constants;
import com.squareup.picasso.Picasso;

/**
 * Created by PRASAD on 01-Nov-18.
 */

public class MainBannerImagesAdapter extends PagerAdapter {
    private Context context;
    private ShopOverviewPojo shopOverviewPojo;
    LayoutInflater mLayoutInflater;

    public MainBannerImagesAdapter(ShopDetailsActivity shopDetailsActivity, ShopOverviewPojo shopOverviewPojo) {
        this.context=shopDetailsActivity;
        this.shopOverviewPojo=shopOverviewPojo;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

//    public MainBannerImagesAdapter(HomeMainCategoriesActivity homeMainCategoriesActivity, MainBannerImagesPojo mainBannerImagesPojo) {
//        this.context = homeMainCategoriesActivity;
//        this.mainBannerImagesPojo = mainBannerImagesPojo;
//        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//
//    }

    @Override
    public int getCount() {
        return shopOverviewPojo.getResponse().getShop_banners().size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.pager_item, null);
        String id;
        //     final LinearLayout lv_image_click=itemView.findViewById(R.id.lv_image);
        //  View itemView = mLayoutInflater.inflate(R.layout.pager_item,container,false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + shopOverviewPojo.getResponse().getShop_banners().get(position).getImage())
                .into(imageView);

           /* LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(950, 950);
            imageView.setLayoutParams(layoutParams);*/
//        lv_image_click.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                 String id=mainBannerImagesPojo.getResponse().get(getItemPosition(lv_image_click)).getId();
//                Toast.makeText(context,"ID is"+id,Toast.LENGTH_SHORT).show();
//            }
//        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences spf_shop_list=context.getSharedPreferences("MySpf_shop_list",Context.MODE_PRIVATE);
//                SharedPreferences.Editor spf_edit=spf_shop_list.edit();
//                spf_edit.putString("Vendor_ID",mainBannerImagesPojo.getResponse().get(position).getVendor_id());
//                spf_edit.commit();
//                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_VENDOR_SHOP_DETAILS);
//            }
//        });
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
