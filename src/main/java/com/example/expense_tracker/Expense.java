package com.example.expense_tracker;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private double amount;
    private String description;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private LocalDate date;


    public Expense() {
    }

    public Expense(String title, double amount, String description, String category, LocalDate date) {
        this.title = title;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}

