package com.example.hellospring;

// ВАЖНО: DTO (Data Transfer Object) - ОБЪЕКТ ДЛЯ ПЕРЕДАЧИ ДАННЫХ
// НАЗНАЧЕНИЕ: Переносить данные из Service в Controller БЕЗ СВЯЗИ С БАЗОЙ
// ПРЕИМУЩЕСТВА DTO:
// 1. ИЗОЛЯЦИЯ: Controller не знает о структуре базы данных
// 2. БЕЗОПАСНОСТЬ: Можем скрыть чувствительные данные
// 3. ГИБКОСТЬ: Можем преобразовывать данные (форматировать даты, вычислять поля)
// 4. СТАБИЛЬНОСТЬ: При изменении базы DTO остается неизменным
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
