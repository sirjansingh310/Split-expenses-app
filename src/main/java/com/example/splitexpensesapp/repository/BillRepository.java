package com.example.splitexpensesapp.repository;

import com.example.splitexpensesapp.model.Bill;
import com.example.splitexpensesapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByUser(User user);
}
