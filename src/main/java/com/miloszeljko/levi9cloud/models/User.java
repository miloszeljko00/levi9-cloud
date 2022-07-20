package com.miloszeljko.levi9cloud.models;

import com.miloszeljko.levi9cloud.model.CostsDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class User {
    // List of all users that performed POST request
    public static List<User> users = new ArrayList<>();

    private BigDecimal userId;
    private List<Payload> payloads = new ArrayList<>();

    private User(BigDecimal userId) {
        this.userId = userId;
    }

    // If user already exists return it, else return new instance of object
    public static User findUser(BigDecimal userId){
        if(!users.isEmpty()){
            for(User user : users){
                if(user.userId.equals(userId)){
                    return user;
                }
            }
        }
        User user = new User(userId);
        users.add(user);
        return user;
    }

    public static List<User> getUsers() {
        return users;
    }

    public void addPayload(Payload payload){
        payloads.add(payload);
    }
    public static void setUsers(List<User> users) {
        User.users = users;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public List<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(List<Payload> payloads) {
        this.payloads = payloads;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", payloads=" + payloads +
                '}';
    }
}
