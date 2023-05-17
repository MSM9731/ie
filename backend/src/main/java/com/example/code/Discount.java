package com.example.code;

import java.util.Arrays;

public class Discount {

    private String discountCode;
    private int discount;
    private User[] users;

    public String getDiscountCode() {
        return discountCode;
    }

    public int getDiscount() {
        return discount;
    }

    public Discount(String discountCode, int discount) {
        this.discountCode = discountCode;
        this.discount = discount;
        this.users = new User[0];
    }
    public boolean isUser(String username){
        for(User user:this.users){
            if(user.getUsername().equals(username))
                return true;
        }
        return false;
    }
    public void addUser(User newUser){
        this.users = Arrays.copyOf(this.users, this.users.length + 1);
        this.users[this.users.length - 1] = newUser;
    }
}
