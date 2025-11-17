package com.example.hellospring;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CalculationHistoryDTO {

    // ПОЛЯ DTO - ТОЛЬКО ТО, ЧТО НУЖНО ДЛЯ ОТОБРАЖЕНИЯ
    private final Long id;
    private final String operation;
    private final String expression;
    private final double result;
    private final LocalDateTime timestamp;

    // КОНСТРУКТОР - создает DTO из Entity
    public CalculationHistoryDTO(Long id, String operation, String expression, double result, LocalDateTime timestamp) {
        this.id = id;
        this.operation = operation;
        this.expression = expression;
        this.result = result;
        this.timestamp = timestamp;
    }

    // ГЕТТЕРЫ - позволяют читать данные
    public Long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // ДОПОЛНИТЕЛЬНЫЕ МЕТОДЫ - можно добавить логику форматирования
    // Например: красивое отображение даты/времени
    public String getFormattedTimestamp() {
        return timestamp.toString().replace("T", " ");
    }

    // МОЖНО ДОБАВИТЬ ВЫЧИСЛЯЕМЫЕ ПОЛЯ
    public String getOperationSymbol() {
        switch (operation) {
            case "add":
                return "+";
            case "subtract":
                return "-";
            case "multiply":
                return "×";
            case "divide":
                return "÷";
            default:
                return "?";
        }
    }
}

