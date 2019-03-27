package com.iprismtech.tahyba.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.activities.ShopsListActivity;
import com.iprismtech.tahyba.pojo.ShopsListPojo;
import com.iprismtech.tahyba.utilities.Constants;
import com.squareup.picasso.Picasso;

public class ShopsListAdapter extends RecyclerView.Adapter<ShopsListAdapter.ViewHolder> {
    private Context context;
    private ShopsListPojo shopsListPojo;

    public ShopsListAdapter(ShopsListActivity shopsListActivity, ShopsListPojo shopsListPojo) {
        this.context = shopsListActivity;
        this.shopsListPojo = shopsListPojo;
    }

    @NonNull
    @Override
    public ShopsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_shops, viewGroup, false);
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
    public void onBindViewHolder(@NonNull ShopsListAdapter.ViewHolder viewHolder, int i) {
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + shopsListPojo.getResponse().get(i).getImage())
                .error(R.drawable.dummy)
                .into(viewHolder.iv_shop_img);
        viewHolder.tv_shop_name.setText(shopsListPojo.getResponse().get(i).getShop_name());
        viewHolder.tv_shop_address.setText(shopsListPojo.getResponse().get(i).getAddress());
        viewHolder.tv_shop_number.setText(shopsListPojo.getResponse().get(i).getMobile());
        viewHolder.tv_shop_rating.setText(shopsListPojo.getResponse().get(i).getAvgRate());
//        if (shopsListPojo.getResponse().get(i).getOpen_status().equalsIgnoreCase("NO")) {
//            viewHolder.tv_shop_open_status.setText("Closed");
//        }
//        else
        viewHolder.rating_shop.setRating(Float.parseFloat(shopsListPojo.getResponse().get(i).getAvgRate()));

        viewHolder.tv_shop_open_status.setText("Open Hour:" + shopsListPojo.getResponse().get(i).getOpen_hour() + ":" + "Close hour: " + shopsListPojo.getResponse().get(i).getClose_hour());
    }

    @Override
    public int getItemCount() {
        return shopsListPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_shop_img;
        TextView tv_shop_name, tv_shop_rating, tv_shop_address, tv_shop_number, tv_shop_open_status;
        RatingBar rating_shop;
        CardView cv_item_shop_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_shop_img = itemView.findViewById(R.id.iv_shop_img);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_shop_address = itemView.findViewById(R.id.tv_shop_address);
            tv_shop_number = itemView.findViewById(R.id.tv_shop_number);
            tv_shop_open_status = itemView.findViewById(R.id.tv_shop_open_status);
            tv_shop_rating = itemView.findViewById(R.id.tv_shop_rating);
            rating_shop = itemView.findViewById(R.id.rating_shop);
            cv_item_shop_list = itemView.findViewById(R.id.cv_item_shop_list);
            cv_item_shop_list.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
