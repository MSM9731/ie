package com.example.code;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Data {
    public static DataHandler dataHandler = new DataHandler();
    private static User user = null;

    public static boolean isLogin(){
        if(user==null)
            return false;
        return true;
    }

    public static String getUser() {
        return user.getUsername();
    }
    public static String getUserEmail() {
        return user.getEmail();
    }
    public static void setUser(String username) {
        Data.user = Data.dataHandler.getUser(username);
    }
    public static void removeUser() {
        Data.user = null;
    }
    public static void  getData(){
        String url = "http://5.253.25.110:5000";
        String[] api  = new String[]{"/api/users","/api/providers","/api/v2/commodities","/api/comments" , "/api/discount"};

        HttpClient client = HttpClient.newHttpClient();

        for (int i=0;i <api.length;i++ ){
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url + api[i]))
                        .build();
                HttpResponse<String> response = client.send(request,
                        HttpResponse.BodyHandlers.ofString());
                JSONArray data = new JSONArray(response.body());
                for (int j = 0 ; j < data.length(); j++) {
                    JSONObject obj = data.getJSONObject(j);
                    JSONObject r ;
                    if(i==0)
                     r= dataHandler.addUser(obj);
                    else if (i==1)
                        r= dataHandler.addProvider(obj);
                    else if (i==2)
                        r= dataHandler.addCommodity(obj);
                    else if (i==3)
                        r= dataHandler.addComment(obj);
                    else
                        r= dataHandler.addDiscount(obj);

                    System.out.println(r.toString());
                }

            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }


}
