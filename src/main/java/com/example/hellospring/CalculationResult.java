package com.example.hellospring;

public class CalculationResult {
    private final double result;
    private final Long recordId;

    public CalculationResult(double result, Long recordId) {
        this.result = result;
        this.recordId = recordId;
    }
    // Позволяют другим классам получить значения, но не изменить их
    public double getResult() {
        return result;
    }

    public Long getRecordId() {
        return recordId;
    }
}
