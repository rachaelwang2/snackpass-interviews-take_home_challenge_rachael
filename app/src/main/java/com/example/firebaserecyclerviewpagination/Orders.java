package com.example.firebaserecyclerviewpagination;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@RequiresApi(api = Build.VERSION_CODES.O)
public class Orders {
    private String order_id, order_date, item_name, quantity, product_price, total_products;
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    String currentTime = "03/08/2019 21:58";

    public Orders(){}

    public Orders(String item_name, String order_date, String quantity, String product_price){
        this.item_name = item_name;
        this.order_date = order_date;
        this.quantity = quantity;
        this.product_price = product_price;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Orders(String order_id, String order_date, String item_name,
                  String quantity, String product_price, String total_products){
        this.item_name = item_name;
        this.order_id = order_id;
        this.order_date = order_date;
        this.quantity = quantity;
        this.product_price = product_price;
        this.total_products = total_products;
    }
    public String getTotal_products() {
        return total_products;
    }


    public String getItem_name() {
        return item_name;
    }

    //returns whether or not the item is trending
    public boolean getTrending() {
        LocalDateTime date1 = LocalDateTime.parse(currentTime, dateFormat);
        LocalDateTime date2 = LocalDateTime.parse(order_date, dateFormat);
        long hours = ChronoUnit.HOURS.between(date2, date1);
        if (hours > 47) {
            return false;
        } else {
            return true;
        }
    }

    public long getTime_date() {
        LocalDateTime date1 = LocalDateTime.parse(currentTime, dateFormat);
        LocalDateTime date2 = LocalDateTime.parse(order_date, dateFormat);
        long elapsed_minutes = ChronoUnit.MINUTES.between(date2, date1);
        return elapsed_minutes;
    }

    public String getOrder_date() {
        LocalDateTime date1 = LocalDateTime.parse(currentTime, dateFormat);
        LocalDateTime date2 = LocalDateTime.parse(order_date, dateFormat);
        long elapsed_minutes = ChronoUnit.MINUTES.between(date2, date1);
        if (elapsed_minutes < 60) {
            return "ordered " + String.valueOf(elapsed_minutes) + " mins ago";
        } else {
            long hours = elapsed_minutes/60;
            long minutes = elapsed_minutes % 60;
            return "ordered " + String.valueOf(hours) + " hrs " + String.valueOf(minutes) + " mins ago";

        }
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getProduct_price() {
        return "$" + product_price;
    }

    public String getQuantity() {
        return quantity;
    }


    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }


    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setTotal_products(String total_products) {
        this.total_products = total_products;
    }






}
