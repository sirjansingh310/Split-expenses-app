package com.example.splitexpensesapp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BillRequestDTO {
    @NotNull
    private Integer userId;
    private String description;
    @NotNull
    private List<Integer> sharedAmong;
    @NotNull
    private Double amount;
    public BillRequestDTO(){

    }
    public BillRequestDTO(@NotEmpty Integer userId, String description, @NotEmpty List<Integer> sharedAmong, @NotEmpty Double amount) {
        this.userId = userId;
        this.description = description;
        this.sharedAmong = sharedAmong;
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getSharedAmong() {
        return sharedAmong;
    }

    public void setSharedAmong(List<Integer> sharedAmong) {
        this.sharedAmong = sharedAmong;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
