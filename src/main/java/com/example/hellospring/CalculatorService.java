package com.example.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {
    // Spring автоматически находит CalculationRepository и "вставляет" его сюда
    // Это называется Dependency Injection (внедрение зависимостей)
    @Autowired
    private CalculationRepository calculationRepository;

    // Метод сложения
    public CalculationResult add(int a, int b) {
        int result = a + b;

        //СОЗДАЕМ ЗАПИСЬ ДЛЯ БАЗЫ ДАННЫХ
        Calculation calculation = new Calculation("add", a + " + " + b, result);

        // СОХРАНЯЕМ В БАЗУ ДАННЫХ
        // calculationRepository.save() - превращает Java-объект в SQL INSERT
        calculationRepository.save(calculation);

        // 4. ВОЗВРАЩАЕМ РЕЗУЛЬТАТ В КОНТРОЛЛЕР
        // Создаем специальный объект-переноску для данных
        return new CalculationResult(result, calculation.getId());
    }

    //Метод вычитания
    public CalculationResult subtract(int a, int b) {
        int result = a - b;

        Calculation calculation = new Calculation("subtract", a + " - " + b, result);
        calculationRepository.save(calculation);

        return new CalculationResult(result, calculation.getId());
    }

    //Метод для умножения
    public CalculationResult multiply(double a, double b) {
        double result = a * b;

        Calculation calculation = new Calculation("multiply", a + " * " + b, result);
        calculationRepository.save(calculation);

        return new CalculationResult(result, calculation.getId());
    }

    //Метод деления
    public CalculationResult divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("На ноль делить нельзя");
        }

        double result = a / b;

        Calculation calculation = new Calculation("divide", a + " / " + b, result);
        calculationRepository.save(calculation);

        return new CalculationResult(result, calculation.getId());
    }

    //Метод вывода и подсчета истории операций
    // Controller решает КАК отображать, Service - ЧТО отображать
    public List<Calculation> getCalculationHistory() {
        // Service может добавлять бизнес-логику
        // Например: сортировка, фильтрация, пагинация
        return calculationRepository.findAll();
    }
}
