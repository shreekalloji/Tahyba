package com.iprismtech.tahyba.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iprismtech.tahyba.R;
import com.iprismtech.tahyba.activities.ShopsSearchActivity;
import com.iprismtech.tahyba.pojo.ShopsSearchPojo;

public class ShopSearchAdapter extends RecyclerView.Adapter<ShopSearchAdapter.ViewHolder> {
    private Context context;
    private ShopsSearchPojo shopsSearchPojo;

    public ShopSearchAdapter(ShopsSearchActivity shopsSearchActivity, ShopsSearchPojo shopsSearchPojo) {
        this.context = shopsSearchActivity;
        this.shopsSearchPojo = shopsSearchPojo;
    }

    private OnitemClickListener mListner;

    public void setOnItemClickListener(OnitemClickListener onitemClickListener) {
        mListner = onitemClickListener;
    }

    public interface OnitemClickListener {
        void onItemClick(View view, int position);
    }


    @NonNull
    @Override
    public ShopSearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_results, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopSearchAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv_shop_name.setText(shopsSearchPojo.getResponse().get(i).getShop_name());
        viewHolder.tv_shop_address.setText(shopsSearchPojo.getResponse().get(i).getSub_location());
        viewHolder.tv_distance.setText(Math.round(Float.valueOf((shopsSearchPojo.getResponse().get(i).getShort_distance()))) + "KM");

    }

    @Override
    public int getItemCount() {
        return shopsSearchPojo.getResponse().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_shop_name, tv_shop_address, tv_distance;
        LinearLayout ll_item_search;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_shop_name = itemView.findViewById(R.id.tv_shop_name);
            tv_shop_address = itemView.findViewById(R.id.tv_shop_address);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            ll_item_search = itemView.findViewById(R.id.ll_item_search);
            ll_item_search.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListner != null) {
                mListner.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
