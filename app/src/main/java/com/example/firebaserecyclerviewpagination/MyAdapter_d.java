package com.example.firebaserecyclerviewpagination;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter_d  extends RecyclerView.Adapter<MyAdapter_d.Holder> {
    List<Orders> userList;
    Context context;

    public MyAdapter_d(Context context) {
        this.userList = new ArrayList<>();
        this.context = context;
    }

    public void addAll(List<Orders> newOrders) {
        int initSize = userList.size();
        userList.addAll(newOrders);
        notifyItemRangeChanged(initSize, newOrders.size());
    }

    public String getLastItemId(){
        return userList.get(userList.size() - 1).getOrder_id();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new Holder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.name_main.setText(userList.get(position).getItem_name());
        holder.price_main.setText(userList.get(position).getProduct_price());
        holder.time_main.setText(userList.get(position).getOrder_date());
        holder.recent_purchase_main.setText(MainActivity_d.getRecentPurchases(userList.get(position).getItem_name()));
        int[] images = {R.drawable.icon1,R.drawable.icon2,R.drawable.icon3,R.drawable.icon4, R.drawable.icon5};
        Random rand = new Random();
        holder.image_main.setImageResource(images[rand.nextInt(images.length)]);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name_main, recent_purchase_main, price_main, time_main;
        CircleImageView image_main;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name_main = (TextView)itemView.findViewById(R.id.name);
            recent_purchase_main = (TextView)itemView.findViewById(R.id.recent_purchases);
            price_main = (TextView)itemView.findViewById(R.id.price);
            time_main = (TextView)itemView.findViewById(R.id.time);
            image_main = (CircleImageView)itemView.findViewById(R.id.iv);

        }
    }
}
