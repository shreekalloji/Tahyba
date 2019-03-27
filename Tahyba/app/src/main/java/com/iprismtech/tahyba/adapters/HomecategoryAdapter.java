package com.iprismtech.tahyba.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.tahyba.MainActivity;
import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.factories.Constants.AppConstants;
import com.iprismtech.tahyba.factories.controllers.ApplicationController;
import com.iprismtech.tahyba.fragments.HomeFragment;
import com.iprismtech.tahyba.pojo.MainCategoriesPojo;
import com.iprismtech.tahyba.utilities.Constants;
import com.squareup.picasso.Picasso;

public class HomecategoryAdapter extends RecyclerView.Adapter<HomecategoryAdapter.ViewHolder> {
    private Context context;
    private MainCategoriesPojo mainCategoriesPojo;
    private MainActivity mainActivity;

    public HomecategoryAdapter(FragmentActivity activity, MainCategoriesPojo mainCategoriesPojo) {
        this.context = activity;
        this.mainCategoriesPojo = mainCategoriesPojo;
    }

    @NonNull
    @Override
    public HomecategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_categories, viewGroup, false);
        return new ViewHolder(view);
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public void onBindViewHolder(@NonNull HomecategoryAdapter.ViewHolder viewHolder, int i) {
//        Picasso.with(context)
//                .load(Constants.BASE_IMAGE_URL + mainCategoriesPojo.getResponse().get(i).getMobicon())
//                .error(R.drawable.dummy)
//                .into(viewHolder.iv_item_home);
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + mainCategoriesPojo.getResponse().get(i).getXsmob_banner())
                .error(R.drawable.dummy)
                .into(viewHolder.iv_item_img2);
        viewHolder.tv_cat_name.setText(mainCategoriesPojo.getResponse().get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mainCategoriesPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_item_home, iv_item_img2;
        LinearLayout ll_item_main;
        TextView tv_cat_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_item_home = itemView.findViewById(R.id.iv_item_home);
            ll_item_main = itemView.findViewById(R.id.ll_item_main);
            iv_item_img2 = itemView.findViewById(R.id.iv_item_img2);
            tv_cat_name = itemView.findViewById(R.id.tv_cat_name);
            ll_item_main.setOnClickListener(this);
//            ll_item_main.setOnClickListener(new View.OnClickListener() {
//                @SuppressLint("WrongConstant")
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("Key_shopId", mainCategoriesPojo.getResponse().get(getAdapterPosition()).getId());
//                    bundle.putString("Key_small_icon", mainCategoriesPojo.getResponse().get(getAdapterPosition()).getMobicon());
//                    bundle.putString("Key_Banner_icon", mainCategoriesPojo.getResponse().get(getAdapterPosition()).getSmmob_banner());
//
//                    ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_SHOPS_LIST_SCREEN, bundle);
//
//                }
//            });

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }

}
