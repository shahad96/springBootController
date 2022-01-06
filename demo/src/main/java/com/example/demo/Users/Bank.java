package com.example.demo.Users;

import java.util.ArrayList;

public class Bank {
    
    private double balance = 10000000.0;
    private ArrayList<User> users = new ArrayList<User>();

    public Bank() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(User user) {
        this.users.add(user);
    }
}
