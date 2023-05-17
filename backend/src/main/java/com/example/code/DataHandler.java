package com.example.code;

import org.json.*;
import java.util.Arrays;
import java.util.Comparator;

public class DataHandler {
    User[] users;
    Provider[] providers;
    Commodity[] commodities;
    Comment[] comments;
    Discount[] discounts;
    int commentId;

    private boolean checkUser(String username){
        for(User user: this.users)
            if (user.username.equals(username))
                return  true;
        return false;
    }
    public User getUser(String username){
        for(User user: this.users)
            if (user.username.equals(username))
                return  user;
        return null;
    }
    private User getUserByEmail(String email){
        for(User user: this.users)
            if (user.email.equals(email))
                return  user;
        return null;
    }
    private Discount getDiscount(String discountcode){
        for(Discount discount: this.discounts)
            if (discount.getDiscountCode().equals(discountcode))
                return  discount;
        return null;
    }
    private boolean checkCommodity(int id){
        for(Commodity commodity: this.commodities)
            if (commodity.id == id)
                return  true;
        return false;
    }
    private Commodity getCommodity(int id){
        for(Commodity commodity: this.commodities)
            if (commodity.id == id)
                return  commodity;
        return null;
    }
    private Comment getComment(int id){
        for(Comment comment: this.comments)
            if (comment.id == id)
                return  comment;
        return null;
    }
    private boolean checkProvider(int id){
        for(Provider provider:this.providers)
            if (provider.id==id)
                return  true;
        return false;
    }
    private Provider getProvider(int id){
        for(Provider provider:this.providers)
            if (provider.id==id)
                return  provider;
        return null;
    }
    private JSONObject response(boolean status , String message){
        JSONObject data = new JSONObject();
        data.put("data",message);
        data.put("status",status);
        return data;
    }

    private JSONObject response(boolean status , JSONObject message){
        JSONObject data = new JSONObject();
        data.put("data",message);
        data.put("status",status);
        return data;
    }
    public JSONObject addUser(JSONObject data) {
        try {
            String username = data.getString("username");
            String password = data.getString("password");
            String email = data.getString("email");
            String birthDate = data.getString("birthDate");
            String address = data.getString("address");
            double credit = data.getDouble("credit");
//            {"username":"amin","password":"1234","email":"amin@yahoo.com","birthDate":"12-21-2002","credit":1500,"address":"12"}
            User user = getUser(username);
            if(user == null){
                if (!username.matches("[A-Za-z0-9]+"))
                    return this.response(false,"username isn't correct.");
                User newUser = new User(username,password,email,birthDate,address,credit);
                this.users = Arrays.copyOf(this.users, this.users.length + 1);
                this.users[this.users.length - 1] = newUser;
                return this.response(true,"add new user.");
            }else {
                user.update(username,password,email,birthDate,address,credit);
                return this.response(true,"updated user.");
            }
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }


    }
    public JSONObject addProvider(JSONObject data) {
        try {
            String name = data.getString("name");
            String registryDate = data.getString("registryDate");
            int id = data.getInt("id");
            Provider provider = this.getProvider(id);
            if(provider==null){
                Provider newProvider = new Provider(id,name,registryDate);
                this.providers = Arrays.copyOf(this.providers, this.providers.length + 1);
                providers[providers.length - 1] = newProvider;
                return this.response(true,"add new provider.");
            }else {
                provider.update(id,name,registryDate);
                return this.response(true,"updated provider.");
            }
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getProviderById(JSONObject data) {
        try {
            int id = data.getInt("id");
            Provider provider = this.getProvider(id);
            if(provider==null){
                return this.response(false,"provider with this id exist!!!.");
            }else {
                return this.response(true,provider.getInformation().toString());
            }
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject addCommodity(JSONObject data){
        try {
            int id = data.getInt("id");
            String name=data.getString("name");
            String image=data.getString("image");
            int providerId = data.getInt("providerId");
            double price = data.getDouble("price");
            JSONArray categories = data.getJSONArray("categories");
            double rating = data.getDouble("rating");
            int inStock = data.getInt("inStock");

            Provider provider = this.getProvider(providerId);
            if(this.checkCommodity(id))
                return this.response(false,"commodity with this id exist!!!");
            if(provider!=null){
                String[] categoriesArr = new String[categories.length()];
                for (int i = 0; i < categories.length(); ++i)
                    categoriesArr[i] = categories.getString(i);

                Commodity commodity = new Commodity(id,name,price,categoriesArr,rating,inStock,provider,image);
                this.commodities = Arrays.copyOf(this.commodities, this.commodities.length + 1);
                commodities[commodities.length - 1] = commodity;
                return this.response(true,"add new commodity.");
            }else {
                return this.response(false,"provider with this id doesn't exist!!!");
            }
        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getCommoditiesList(){
        JSONArray data = new JSONArray();
       for(int i =0 ; i<this.commodities.length;i++){
            Commodity commodity = this.commodities[i];
            data.put(commodity.getInformation());
       }
        JSONObject result = new JSONObject()
                .put("commoditiesList", data);

       return  result;
    }
    public JSONObject rateCommodity(JSONObject data){
        try {
            int commodityId = data.getInt("commodityId");
            String username=data.getString("username");
            int score = data.getInt("score");
            Commodity commodity = this.getCommodity(commodityId);
            User user = this.getUser(username);

            if(user==null)
                return response(false,"user doesn't exist!!!");
            if(commodity==null)
                return response(false,"commodity doesn't exist!!!");
            if(score>10 || score<1)
                return response(false,"score must been between 1 and 10!!!");
            String ans = commodity.addScore(user,score);
            return response(true , ans);

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject addToBuyList(JSONObject data){
        try {
            int commodityId = data.getInt("commodityId");
            String username=data.getString("username");

            Commodity commodity = this.getCommodity(commodityId);
            User user = this.getUser(username);

            if(commodity==null)
                return response(false,"commodity doesn't exist!!!");

            if(user==null)
                return response(false,"user doesn't exist!!!");
            if(commodity.inStock<=0)
                return response(false,"commodity doesn't in stock.");
            if(user.cart.checkCart(commodityId))
                return response(false,"commodity has been added to cart already.");

            user.cart.addCart(commodity);
//            commodity.inStock --;
            return response(true , "added to cart");

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject removeFromBuyList(JSONObject data){
        try {
            int commodityId = data.getInt("commodityId");
            String username=data.getString("username");

            Commodity commodity = this.getCommodity(commodityId);
            User user = this.getUser(username);

            if(commodity==null)
                return response(false,"commodity doesn't exist!!!");

            if(user==null)
                return response(false,"user doesn't exist!!!");

            if(!user.cart.checkCart(commodityId))
                return response(false,"commodity isn't in the cart.");

            user.cart.removeCart(commodity);
//            commodity.inStock ++;
            return response(true , "removed from cart");

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    private JSONArray findSuggestion(Commodity selectedCommodity){

        JSONObject[] result= new JSONObject[0];
        System.out.println(commodities.length);
        for(int i =0 ; i<this.commodities.length ; i++) {
            Commodity commodity = this.commodities[i];
            if(commodity.id == selectedCommodity.id)
                continue;
            int isInSameCategory =0;
            for(int j = 0 ; j<selectedCommodity.categories.length ; j++){
                String category = selectedCommodity.categories[j];
                if(commodity.hasCategory(category)){
                    isInSameCategory=1;
                    break;
                }
            }

            double score = (11 * isInSameCategory) + commodity.getRating();
            JSONObject data = commodity.getInformation();
            data.put("score" , score);
            result = Arrays.copyOf(result, result.length + 1);
            result[result.length - 1] = data;
        }
        Arrays.sort(result, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                return o2.getInt("score") - o1.getInt("score"); // or whatever property you want to sort
            }
        });
        JSONArray finalResult = new JSONArray();
        for(int i = 0 ; i < 5 ; i ++ ){
            finalResult.put(result[i]);
        }
        return finalResult;
    }
    public JSONObject getCommodityById(JSONObject data){
        try {
            int commodityId = data.getInt("id");
            Commodity commodity = this.getCommodity(commodityId);

            if(commodity==null)
                return response(false,"commodity doesn't exist!!!");

            JSONObject ans = commodity.getInformation();
            ans.put("suggestion" , findSuggestion(commodity));

            return response(true , ans);

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getCommoditiesByCategory(JSONObject data){
        try {
            String category = data.getString("category");
            JSONArray information = new JSONArray();
            for(int i =0 ; i<this.commodities.length;i++){
                Commodity commodity = this.commodities[i];
                if(commodity.hasCategory(category)){
                    information.put(commodity.getInformation());
                }
            }
            String dataString = new JSONObject()
                    .put("commoditiesListByCategory", information)
                    .toString();
            return  this.response(true,dataString);

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getCommoditiesByName(JSONObject data){
        try {

            String name = data.getString("name");
            JSONArray information = new JSONArray();
            for(int i =0 ; i<this.commodities.length;i++){
                Commodity commodity = this.commodities[i];
                if(commodity.name.toLowerCase().contains(name.toLowerCase())){
                    information.put(commodity.getInformation());
                }
            }
            String dataString = new JSONObject()
                    .put("commoditiesListByName", information)
                    .toString();
            return  this.response(true,dataString);

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getCommoditiesByPrice(JSONObject data){
        try {
            Integer start = data.getInt("start");
            Integer end = data.getInt("end");
            JSONArray information = new JSONArray();
            for(int i =0 ; i<this.commodities.length;i++){
                Commodity commodity = this.commodities[i];
                if(commodity.checkPrice(start , end)){
                    information.put(commodity.getInformation());
                }
            }
            String dataString = new JSONObject()
                    .put("commoditiesListByCategory", information)
                    .toString();
            return  this.response(true,dataString);

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getBuyList(JSONObject data){
        try {
            String username = data.getString("username");
            User user = this.getUser(username);
            if(user==null)
                return response(false,"user doesn't exist!!!");
            JSONArray information = user.cart.getInformation();
            String dataString = new JSONObject()
                    .put("buyList", information)
                    .toString();
            return  this.response(true,dataString);
        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getCommoditiesBySort(JSONObject data){
        try {
            boolean ascending = data.getBoolean("sort");
            Commodity copyCommodity[] =  this.commodities.clone();
            Arrays.sort(copyCommodity, Comparator.comparing(Commodity::getPrice));
            JSONArray information = new JSONArray();
            if(ascending){
                for(int i =copyCommodity.length-1 ; i>=0;i--){
                    Commodity commodity = copyCommodity[i];
                    information.put(commodity.getInformation());
                }
            }else {
                for(int i =0 ; i<copyCommodity.length;i++){
                    Commodity commodity = copyCommodity[i];
                    information.put(commodity.getInformation());
                }
            }

            String dataString = new JSONObject()
                    .put("getCommoditiesBySort", information)
                    .toString();
            return  this.response(true,dataString);

        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject buyCart(JSONObject data){
        try {
            String username=data.getString("username");

            User user = this.getUser(username);

            if(user==null)
                return response(false,"user doesn't exist!!!");
            if(user.checkSum()){
                user.buyCart();
                return response(true , "bought successfully.");
            }else{
                return response(false,"user doesn't have credit!!!");
            }



        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject getUserById(JSONObject data) {
        try {
            String username = data.getString("username");
            User user = this.getUser(username);
            if(user==null){
                return this.response(false,"user with this id exist!!!.");
            }else {
                return this.response(true,user.getInformation().toString());
            }
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject increaseUserCredit(JSONObject data) {
        try {
            Double credit = data.getDouble("credit");
            String username = data.getString("username");
            User user = this.getUser(username);
            if(user==null){
                return this.response(false,"user with this id doesn't exist!!!.");
            }else {
                Boolean ans = user.increaseCredit(credit);
                if (ans)
                    return this.response(true,"Increased successfully.");
                else
                    return this.response(false,"credit must be positive.");
            }
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }


    public JSONObject addComment(JSONObject data) {
        try {
            String userEmail = data.getString("userEmail");
            int commodityId= data.getInt("commodityId");
            String text= data.getString("text");
            String date= data.getString("date");
            Commodity commodity = this.getCommodity(commodityId);
            User user = this.getUserByEmail(userEmail);

            if(user==null)
                return response(false,"user doesn't exist!!!");

            if(commodity==null)
                return response(false,"commodity doesn't exist!!!");

            Comment newComment = new Comment(user,commodity,text,date , this.commentId);
            this.comments = Arrays.copyOf(this.comments, this.comments.length + 1);
            this.comments[this.comments.length - 1] = newComment;
            commodity.addComment(newComment);
            this.commentId +=1;

            return response(true,"add new comment.");
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject addCommentVote(JSONObject data) {
        try {
            String username = data.getString("username");
            int commentId= data.getInt("commentId");
            int vote= data.getInt("vote");
            Comment comment = this.getComment(commentId);
            User user = this.getUser(username);

            if(user==null)
                return response(false,"user doesn't exist!!!");

            if(comment==null)
                return response(false,"comment doesn't exist!!!");
            CommentVote commentVote = comment.getUserVote(user);
            if (commentVote!=null){
                if(comment.updateVote(commentVote,vote))
                    return response(true,"update vote successfully.");
                else
                    return response(false,"vote must be -1 or 0 or 1.");

            }else {
                if(comment.addVote(user, vote))
                    return response(true,"add new vote.");
                else
                    return response(false,"vote must be -1 or 0 or 1.");
            }

        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }


    }

    public JSONObject login(JSONObject data){
        try {
            String username = data.getString("username");
            String password = data.getString("password");
            User user = this.getUser(username);
            if(user==null)
                return response(false,"user doesn't exist!!!");
            if(user.password.equals(password))
                return response(true,"login successfully");
            return response(false,"password isn't correct!!!");
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }

    public JSONObject addDiscount(JSONObject data) {
        try {
            String discountCode = data.getString("discountCode");
            int discount= data.getInt("discount");
            Discount newDiscount = new Discount(discountCode , discount);
            this.discounts = Arrays.copyOf(this.discounts, this.discounts.length + 1);
            this.discounts[this.discounts.length - 1] = newDiscount;

            return response(true,"add new discount.");
        }catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public JSONObject applyDiscount(JSONObject data){
        try {
            String username=data.getString("username");
            String discountCode=data.getString("discountCode");
            User user = this.getUser(username);
            Discount discount = this.getDiscount(discountCode);
            if(user==null)
                return response(false,"user doesn't exist!!!");
            if(discount==null)
                return response(false,"discount doesn't exist!!!");
            if(discount.isUser(username))
                return response(false,"discount expired!!!");

            user.applyDiscount(discount);
            return response(true , "applied discount to cart.");
        }
        catch (Exception e){
            return this.response(false,"all field is necessary.");
        }
    }
    public DataHandler() {
            this.users = new User[0];
            this.providers = new Provider[0];
            this.commodities = new Commodity[0];
            this.comments = new Comment[0];
            this.discounts = new Discount[0];
            this.commentId =1;
    }
}
