package com.example.splitexpensesapp.controller;

import com.example.splitexpensesapp.dto.BillRequestDTO;
import com.example.splitexpensesapp.model.Bill;
import com.example.splitexpensesapp.model.User;
import com.example.splitexpensesapp.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @GetMapping("/bill")
    public ResponseEntity<List<Bill>> getAllBills(){
        return new ResponseEntity<>(billRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/bill/{billId}")
    public ResponseEntity<Bill> getBill(@PathVariable Integer billId){
        Optional<Bill> optionalBill = billRepository.findById(billId);
        Bill bill = optionalBill.orElseThrow(() ->new RuntimeException("No bill found with bill id " + billId));
        return new ResponseEntity<>(bill, HttpStatus.OK);
    }
    @PostMapping("/bill")
    public ResponseEntity addBill(@Valid @RequestBody BillRequestDTO billRequestDTO){
       User user = new User();
       user.setId(billRequestDTO.getUserId());
       List<User> sharedAmong = billRequestDTO.getSharedAmong().stream().map(id -> {
           User u = new User();
           u.setId(id);
           return u;
       }).collect(Collectors.toList());
       Bill bill = new Bill();
       bill.setUser(user);
       bill.setDescription(billRequestDTO.getDescription());
       bill.setSharedAmong(sharedAmong);
       bill.setAmount(billRequestDTO.getAmount());
       bill = billRepository.save(bill);
       URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bill.getId()).toUri();
       return ResponseEntity.created(uri).build();
    }
}
