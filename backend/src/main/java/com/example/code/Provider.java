package com.example.code;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

public class Provider {
    int id;
    String name;
    String registerDate;
    Commodity[] commodities;
    double rate;
    public Provider(int id, String name, String registerDate) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
        this.commodities = new Commodity[0];
        this.rate = 0;
    }
    public void update(int id, String name, String registerDate) {
        this.id = id;
        this.name = name;
        this.registerDate = registerDate;
    }
    public void updateRate(){
        double sum =0;
        for(Commodity commodity : this.commodities){
            sum+= commodity.rating;
        }
        this.rate = sum / this.commodities.length;
    }
    public void addCommodity(Commodity commodity){
        this.commodities = Arrays.copyOf(this.commodities, this.commodities.length + 1);
        commodities[commodities.length - 1] = commodity;
        this.updateRate();
    }
    public JSONObject getInformation(){
        JSONObject data = new JSONObject();
        data.put("id",  this.id);
        data.put("name" , this.name);
        data.put("registerDate" , this.registerDate);
        data.put("rate" , this.rate);
        JSONArray commodities = new JSONArray();
        for(int i =0 ; i< this.commodities.length ; i++){
            commodities.put(this.commodities[i].getInformation());
        }
        data.put("commodities" , commodities);
        return data;
    }
}
