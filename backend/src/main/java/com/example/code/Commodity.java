package com.example.code;


import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;

public class Commodity {

    int id;
    String name;
    String image;
    Provider provider;
    double price;
    String[] categories;
    Score[] scores;
    Comment[] comments;

    public double getRating() {
        return rating;
    }

    double rating;
    int inStock;

    public Commodity(int id, String name, double price, String[] categories, double rating, int inStock,Provider provider,String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.rating = rating;
        this.image = image;
        this.inStock = inStock;
        this.provider = provider;
        this.scores = new Score[0];
        this.comments= new Comment[0];
        this.provider.addCommodity(this);
    }

    public JSONObject getInformation(){
        JSONArray comments = new JSONArray();
        for(Comment comment:this.comments)
            comments.put(comment.getInformation());
        JSONObject data =   new JSONObject()
                .put("id", this.id)
                .put("name", this.name)
                .put("providerName" , this.provider.name)
                .put("price" ,  this.price)
                .put("categories", new JSONArray(this.categories))
                .put("rating" , this.rating)
                .put("comments" , comments)
//                .put("ratingP" , this.provider.rate)
                .put("inStock" , this.inStock)
                .put("image" , this.image);
        return data;
    }
    private Score getScore(User user){
        for(Score score:this.scores){
            if(score.user.username.equals(user.username))
                return score;
        }
        return null;
    }
    private void updateScore(){
        double rate = 0;
        for(Score score:this.scores)
            rate += score.score;
        this.rating = rate / this.scores.length;
        this.provider.updateRate();
    }
    public String addScore(User user , int score) {
        Score userScore = this.getScore(user);
        String message = "";
        if (userScore == null) {
            this.scores = Arrays.copyOf(this.scores, this.scores.length + 1);
            this.scores[this.scores.length - 1] = new Score(user, score);
            message ="score added";
        } else {
            userScore.score = score;
            message ="score updated.";
        }
        this.updateScore();
        this.provider.updateRate();
        return  message;
    }

    public double getPrice() {
        return price;
    }

    public boolean hasCategory(String category){
        for(String c :categories){
            if(c.toLowerCase().equals(category.toLowerCase()))
                return true;
        }
        return false;
    }
    public boolean checkPrice(Integer start , Integer end){
        if (start<=this.price && this.price<=end)
            return true;
        return false;
    }

    public void addComment(Comment comment){
        this.comments = Arrays.copyOf(this.comments, this.comments.length + 1);
        this.comments[this.comments.length - 1] = comment;
    }
    public void buy(){
        this.inStock--;
    }
}
