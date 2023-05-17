package com.example.code;

import org.json.*;

import java.util.Arrays;

public class User {
    String username;
    String password;
    String email;
    String birthDate;
    String address;
    double credit;
    Commodity[] purchasedList;
    Cart cart;

    public String getUsername() {
        return username;
    }

    public User(String username, String password, String email, String birthDate, String address, double credit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.credit = credit;
        this.cart = new Cart();
        this.purchasedList = new Commodity[0];
    }
    public void update(String username, String password, String email, String birthDate, String address, double credit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.credit = credit;
    }
    public boolean increaseCredit(double credit ){
        if(credit>=0){
            this.credit+= credit;
            return true;
    }
        return false;
    }
    public boolean checkSum( ){
        if(this.credit >= this.cart.getFinalSum())
                return  true;
        return false;
    }

    public String getEmail() {
        return email;
    }
    public void applyDiscount(Discount discount){
        this.cart.addDiscount(discount);
    }
    public void removeDiscount(Discount discount){
        this.cart.removeDiscount();
    }

    public void buyCart(){
        double sum = this.cart.getFinalSum();
        Commodity[] commodities = this.cart.getCommodities();
        for (int i =0 ; i < commodities.length; i++ ){
            Commodity commodity = commodities[i];
            this.cart.removeCart(commodity);
            commodity.buy();
            this.purchasedList = Arrays.copyOf(this.purchasedList, this.purchasedList.length + 1);
            this.purchasedList[this.purchasedList.length - 1] = commodity;
        }
        this.credit -= sum;
        this.cart.expireDiscount(this);
    }
    public JSONObject getInformation(){
        JSONObject data = new JSONObject();
        data.put("username",  this.username);
        data.put("email" , this.email);
        data.put("birthDate" , this.birthDate);
        data.put("address" , this.address);
        data.put("credit" , this.credit);
//        JSONArray purchasedList = new JSONArray();
//        for(int i =0 ; i< this.purchasedList.length ; i++){
//            purchasedList.put(this.purchasedList[i].getInformation());
//        }
//        data.put("purchasedList" , purchasedList);
        data.put("cart" , this.cart.getInformation());
        data.put("sum" ,this.cart.getSum());
        data.put("finalSum" ,this.cart.getFinalSum());
        return data;
    }

}
