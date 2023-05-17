package com.example.code;


import org.json.JSONArray;

import java.util.Arrays;

public class Cart {
    private Commodity[] commodities;
    private double sum;
    private double finalSum;
    private Discount discount;
    public Cart() {
        this.commodities = new Commodity[0];
        this.sum = 0;
        this.discount = null;
        this.finalSum = 0;
    }

    public boolean checkCart(int id){
        for (Commodity commodity:this.commodities){
            if(commodity.id==id)
                return true;
        }
            return false;
    }


    public void addCart(Commodity commodity){
        this.commodities = Arrays.copyOf(this.commodities, this.commodities.length + 1);
        this.commodities[this.commodities.length - 1] = commodity;
        this.sum += commodity.price;
        this.calcDiscount();
    }

    public void removeCart(Commodity commodity){
        int i, j;
        for (i = 0, j = 0; j <this.commodities.length; j++)
            if (this.commodities[j].id != commodity.id ) {
                this.commodities[i++] = this.commodities[j];
            }
        this.commodities = Arrays.copyOf(this.commodities, i);
        this.sum -= commodity.price;
        this.calcDiscount();
    }

    private void calcDiscount(){
        if(this.discount!=null){
            int discount = this.discount.getDiscount();
            this.finalSum = ((100 - discount) * this.sum ) / 100;
        }
    }

    public void removeDiscount(){
        if(this.discount!=null){
            this.discount = null;
            this.finalSum = 0 ;
        }
    }
    public void addDiscount(Discount discount){
        this.discount = discount;
        this.calcDiscount();
    }
    public void expireDiscount(User user){
        if(this.discount!=null){
            this.discount.addUser(user);
            this.removeDiscount();
        }
    }

    public double getSum() {
        return this.sum;
    }

    public Commodity[] getCommodities() {
        return commodities;
    }

    public double getFinalSum() {
        if(this.discount==null)
            return this.sum;
        else
            return this.finalSum;
    }
    public JSONArray getInformation(){
        JSONArray data = new JSONArray();
        for(int i =0 ; i<this.commodities.length;i++){
            Commodity commodity = this.commodities[i];
            data.put(commodity.getInformation());
        }

        return data;
    }
}
