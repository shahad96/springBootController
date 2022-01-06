package com.example.demo.Users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UsersController {

    Bank bank = new Bank();

    @GetMapping("/users")
    public ArrayList<User> getUser(){
        return bank.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<String> postUser(@RequestBody User user){

        if(user.getName()==null || user.getId()==null || user.getEmail()==null ||
        user.getPassword()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("enter all values");
        }
        else{

            bank.setUsers(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("user added successfully");
        }

    }

    @PutMapping("/users/deposit/{id}")
    public ResponseEntity<String> deposit(@RequestBody User user,
                                          @PathVariable("id") int id){

        if(!bank.getUsers().get(id).getPassword().equals(user.getPassword()) || !bank.getUsers().get(id).getEmail().equals(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login information wrong");
        }else{
                bank.getUsers().get(id).setBalance(bank.getUsers().get(id).getBalance()+ user.getBalance());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("money added successfully your balance now is " +
                        ""+bank.getUsers().get(id).getBalance());
        }
    }

    @PutMapping("/users/withdrow/{id}")
    public ResponseEntity<String> withdrow(@RequestBody User user,
                                          @PathVariable("id") int id){

        if(!bank.getUsers().get(id).getPassword().equals(user.getPassword()) || !bank.getUsers().get(id).getEmail().equals(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login information wrong");
        }else{
            if(bank.getUsers().get(id).getBalance() <  user.getBalance()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your balance not enough");
            }else{
                bank.getUsers().get(id).setBalance(bank.getUsers().get(id).getBalance()- user.getBalance());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("your balance now is " +
                        ""+bank.getUsers().get(id).getBalance());
            }
        }
    }

    @PutMapping("/users/loan/{id}")
    public ResponseEntity<String> loan(@RequestBody User user,
                                           @PathVariable("id") int id){

        if(!bank.getUsers().get(id).getPassword().equals(user.getPassword()) || !bank.getUsers().get(id).getEmail().equals(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login information wrong");
        }else{
            if(bank.getBalance() < user.getBalance()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bank balance not enough");
            }else{
                bank.getUsers().get(id).setBalance(bank.getUsers().get(id).getBalance()+user.getBalance());
                bank.getUsers().get(id).setLoanAmount(user.getBalance());
                bank.setBalance(bank.getBalance()-user.getBalance());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("your balance now is " +
                        ""+bank.getUsers().get(id).getBalance()+"and bank balance is "+bank.getBalance());
            }
        }
    }

    @PutMapping("/users/payOff/{id}")
    public ResponseEntity<String> payOff(@RequestBody User user,
                                       @PathVariable("id") int id){

        if(!bank.getUsers().get(id).getPassword().equals(user.getPassword()) || !bank.getUsers().get(id).getEmail().equals(user.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("login information wrong");
        }else{
            if(bank.getUsers().get(id).getBalance() < user.getBalance()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your balance not enough");
            }else{
                bank.getUsers().get(id).setBalance(bank.getUsers().get(id).getBalance()-user.getBalance());
                bank.getUsers().get(id).setLoanAmount(bank.getUsers().get(id).getLoanAmount()-user.getBalance());
                bank.setBalance(bank.getBalance()+user.getBalance());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("your balance now is " +
                        ""+bank.getUsers().get(id).getBalance()+"and bank balance is "+bank.getBalance());
            }
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){

        if(bank.getUsers().get(id).getLoanAmount() > 0 ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you have a loan");
        }else{
            bank.getUsers().remove(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("user deleted successfully");
        }

    }



















//    @DeleteMapping("users/{index}")
//    public ResponseEntity<String> deleteUser(@PathVariable int index){
//
//        if (users.get(index)==null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user does`t exist");
//        }else{
//            users.remove(index);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("user deleted");
//        }
//
//    }
//
//    @PutMapping("users/{index}")
//    public ResponseEntity<String> updateUser(@PathVariable int index,@RequestBody User user){
//        if (users.get(index)==null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user does`t exist");
//        }
//        else{
//            users.set(index,user);
//            return ResponseEntity.status(HttpStatus.ACCEPTED).body("user name updated to " +
//                    ""+users.get(index).getName());
//        }
//
//    }

}
