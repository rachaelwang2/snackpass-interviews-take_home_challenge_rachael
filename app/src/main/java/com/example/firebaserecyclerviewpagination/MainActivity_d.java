package com.example.firebaserecyclerviewpagination;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class MainActivity_d extends AppCompatActivity {

    RecyclerView recyclerView;
    final int ITEM_LOAD_COUNT = 35;
    int total_item = 0, last_visible_item;
    MyAdapter_d adapter_d;
    boolean isLoading=false, isMaxData=false;
    static HashMap<String, Integer> recent_purchases = new HashMap<String, Integer>();
    String last_node="", last_keys="";

    //to keep track of recent purchases that are loaded
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addDic(HashMap<String, Integer> rt, String item_name, String quantity) {
        if (!rt.containsKey(item_name)) {
            rt.put(item_name, 0);
        }
        rt.replace(item_name, rt.get(item_name) + Integer.valueOf(quantity));
    }

    //return all the values in the directory which have been purchased recently in 48 hrs.
    public static String getRecentPurchases(String item_name){
        String rt = String.valueOf(recent_purchases.get(item_name));
        return rt + " purchased recently";
    }

    class RecentPurchase_and_Time implements Comparator<Orders>
    {
        // Used for sorting heuristically which trending items should be higher or lower
        @RequiresApi(api = Build.VERSION_CODES.O)
        public int compare(Orders a, Orders b)
        {
            long a_min = a.getTime_date();
            long b_min = b.getTime_date();
            long a_num = recent_purchases.get(a.getItem_name());
            long b_num = recent_purchases.get(b.getItem_name());
            return (int) ((b_num - b_min/120) - (a_num - a_min/120));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById((R.id.recyclerView));

        getLastKeyFromFirebase();

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager((layoutManager));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration((dividerItemDecoration));

        adapter_d = new MyAdapter_d(this);
        recyclerView.setAdapter(adapter_d);
        
        getOrders();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                total_item = layoutManager.getItemCount();
                last_visible_item = layoutManager.findLastVisibleItemPosition();

                if(!isLoading && total_item <= ((last_visible_item + ITEM_LOAD_COUNT))){
                    getOrders();
                    isLoading = true;
                }
            }
        });

    }

    private void getOrders() {
        if(!isMaxData) {
            Query query;

            // Firebase doesn't have this functionality, its NoSQL and limited, but here is a SQL solution
            // SELECT item_name, SUM(quantity), product_price
            // GROUP BY item_name, product_price

            if(TextUtils.isEmpty(last_node))
               query = FirebaseDatabase.getInstance()
                       .getReference().child("Orders")
                       .orderByKey().limitToFirst(ITEM_LOAD_COUNT);
            else
                query = FirebaseDatabase.getInstance()
                        .getReference().child("Orders")
                        .orderByKey().startAt(last_node)
                        .limitToFirst(ITEM_LOAD_COUNT);



        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Orders> newOrders = new ArrayList<>();
                int database_location = 0;
                if(dataSnapshot.hasChildren())
                {
                    for(DataSnapshot orderSnapShot:dataSnapshot.getChildren())
                    {
                        //check whether the order was made when the last 48 hours from "03/08/2019 21:58"
                        Orders check_trending = orderSnapShot.getValue(Orders.class);
                        if (check_trending.getTrending() == TRUE && !recent_purchases.containsKey(check_trending.getItem_name())) {
                            newOrders.add(check_trending);
                        }
                        database_location += 1;
                        addDic(recent_purchases, check_trending.getItem_name(), check_trending.getQuantity());
                    }

                    //sort the batch of orders by number of purchases and recent purchasing time
                    Collections.sort(newOrders, new RecentPurchase_and_Time());

                    //infinite scroll, load new orders
                    if (newOrders.size() != 0) {
                        last_node = newOrders.get(newOrders.size() - 1).getOrder_id();
                    } else {
                        last_node = null;
                    }

                    if(last_node != null && !last_node.equals(last_keys))
                        newOrders.remove(newOrders.size() - 1);
                    else
                        last_node = "end";  //Fix error of infinity load final item

                    adapter_d.addAll(newOrders);
                    isLoading = false;
                } else {
                    isLoading = false;
                    isMaxData = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                isLoading = false;
            }
        });

        }
    }

    private void getLastKeyFromFirebase() {
        //query get last key
        Query getLastKey = FirebaseDatabase.getInstance().getReference().child("Orders").orderByKey().limitToLast(1);
        getLastKey.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot lastKey : dataSnapshot.getChildren())
                    last_keys = lastKey.getKey();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity_d.this, "Can't get last key", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
