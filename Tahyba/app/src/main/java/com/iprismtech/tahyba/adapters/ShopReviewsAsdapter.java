package com.iprismtech.tahyba.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.pojo.ShopReviewsPojo;
import com.iprismtech.tahyba.utilities.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopReviewsAsdapter extends RecyclerView.Adapter<ShopReviewsAsdapter.ViewHolder> {

    private Context context;
    private List<ShopReviewsPojo.ResponseBean.ReviewsBean> shopReviewsPojo;

    public ShopReviewsAsdapter(FragmentActivity activity, List<ShopReviewsPojo.ResponseBean.ReviewsBean> shopReviewsPojo) {
        this.context = activity;
        this.shopReviewsPojo = shopReviewsPojo;

    }

    @NonNull
    @Override
    public ShopReviewsAsdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shopratings_reviews, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopReviewsAsdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_username_reviews.setText(shopReviewsPojo.get(i).getFull_name());
        viewHolder.tv_user_rating_reviews.setText(shopReviewsPojo.get(i).getRating());
        viewHolder.tv_user_review.setText(shopReviewsPojo.get(i).getReviews());
        viewHolder.tv_user_postdate.setText(shopReviewsPojo.get(i).getCreated_on());
        viewHolder.rating_user_reviews.setRating(Float.parseFloat(shopReviewsPojo.get(i).getRating()));
        Picasso.with(context)
                .load(Constants.BASE_IMAGE_URL + shopReviewsPojo.get(i).getImage())
                .error(R.drawable.dummy)
                .into(viewHolder.iv_user_img);
    }

    @Override
    public int getItemCount() {
        return shopReviewsPojo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_user_img;
        TextView tv_username_reviews, tv_user_rating_reviews, tv_user_postdate, tv_user_review;
        RatingBar rating_user_reviews;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_user_img = itemView.findViewById(R.id.iv_user_img);
            tv_username_reviews = itemView.findViewById(R.id.tv_username_reviews);
            tv_user_rating_reviews = itemView.findViewById(R.id.tv_user_rating_reviews);
            tv_user_postdate = itemView.findViewById(R.id.tv_user_postdate);
            tv_user_review = itemView.findViewById(R.id.tv_user_review);
            rating_user_reviews = itemView.findViewById(R.id.rating_user_reviews);
        }
    }
}
