package com.example.splitexpensesapp.controller;

import com.example.splitexpensesapp.model.Bill;
import com.example.splitexpensesapp.model.User;
import com.example.splitexpensesapp.repository.BillRepository;
import com.example.splitexpensesapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;

@RestController
public class PayController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/pay/{user1Id}/{user2Id}")
    public ResponseEntity<String> getPaymentDetails(@PathVariable Integer user1Id , @PathVariable Integer user2Id){
        Optional<User> optionalUser1 = userRepository.findById(user1Id);
        User user1 = optionalUser1.orElseThrow(() -> new RuntimeException("No user found with id" + user1Id));
        Optional<User> optionalUser2 = userRepository.findById(user2Id);
        User user2 = optionalUser2.orElseThrow(() -> new RuntimeException("No user found with id " + user2Id));
        Double moneyOwedByFirstUser = getMoneyOwed(user2, user1);
        Double moneyOwedBySecondUser = getMoneyOwed(user1, user2);
        String moneyOwed = "";
        if(moneyOwedByFirstUser > moneyOwedBySecondUser){
            moneyOwed = user1.getName() + " owes " + user2.getName() + " Rs. " + (moneyOwedByFirstUser - moneyOwedBySecondUser);
        }
        else{
            moneyOwed = user2.getName() + " owes " + user1.getName() + " Rs. " + (moneyOwedBySecondUser - moneyOwedByFirstUser);
        }
        return new ResponseEntity<>(moneyOwed, HttpStatus.OK);
    }
    private double getMoneyOwed(User billPayer, User debtor){
        Double debt = 0D;
        List<Bill> billList = billRepository.findByUser(billPayer);
        for(Bill bill : billList){
            List<User> sharedAmong = bill.getSharedAmong();
            if(sharedAmong.contains(debtor))
                debt += (bill.getAmount() / sharedAmong.size());
        }
        return debt;
    }
}
