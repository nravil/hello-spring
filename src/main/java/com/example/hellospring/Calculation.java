package com.example.hellospring;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calculations")
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operation;
    private String expression;
    private double result;
    private LocalDateTime timestamp;

    public Calculation() {
    }

    public Calculation(String operation, String expression, double result) {
        this.operation = operation;
        this.result = result;
        this.expression = expression;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
