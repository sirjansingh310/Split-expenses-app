package com.example.splitexpensesapp.model;

import org.hibernate.annotations.CollectionType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// auto because identity can create issues with sqlserver. Hibernate will choose either sequence or identity automatically with auto option
    private Integer id;
    private String description;
    @Column(name = "timestamp")
    private final LocalDateTime timestamp = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)// again for sqlserver
    private User user;
    @ManyToMany
    private List<User> sharedAmong;

    @Column(nullable = false)
    private Double amount;
    public Bill(){

    }

    public Bill(Integer id, String description, User user, List<User> sharedAmong, Double amount) {
        this.id = id;
        this.description = description;
        this.user = user;
        this.sharedAmong = sharedAmong;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getSharedAmong() {
        return sharedAmong;
    }

    public void setSharedAmong(List<User> sharedAmong) {
        this.sharedAmong = sharedAmong;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", timestamp=" + timestamp +
                ", user=" + user +
                ", sharedAmong=" + sharedAmong +
                ", amount=" + amount +
                '}';
    }
}
