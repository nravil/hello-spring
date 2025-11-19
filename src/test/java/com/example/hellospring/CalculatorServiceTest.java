package com.example.hellospring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalculatorServiceTest {
    // @Mock - создает "заглушку" для зависимостей (не реальная база данных)
    // CalculationRepository - работает с базой, нам не нужна реальная база в тестах
    @Mock
    private CalculationRepository calculationRepository;
    // @InjectMocks - создает тестируемый объект и "вставляет" в него заглушки
    @InjectMocks
    private CalculatorService calculatorService;

    // Arrange - подготовка данных и настройка заглушек
    // Act - выполнение тестируемого метода
    // Assert - проверка результатов
    @Test
    void testAdd_Success() {
        Calculation calculation = new Calculation();
        // ARRANGE - ПОДГОТОВКА:
        // Настраиваем заглушку calculationRepository.save()
        // Когда вызывают save с ЛЮБЫМ Calculation - возвращаем тот же объект с ID=1
        when(calculationRepository.save(any(Calculation.class)))
                .thenAnswer(invocation -> {
                    Calculation calc = invocation.getArgument(0);
                    calc.setId(1L);
                    return calc;
                });
        // ACT - ВЫПОЛНЕНИЕ:
        // Вызываем реальный метод который тестируем
        CalculationResult result = calculatorService.add(2, 3);

        // ASSERT - ПРОВЕРКА:
        // Проверяем что результат правильный
        assertEquals(5, result.getResult(), "2 + 3 должно быть 5");
        assertEquals(1L, result.getRecordId(), "ID записи должен быть 1");

        // ПРОВЕРКА ВЗАИМОДЕЙСТВИЯ:
        // Убеждаемся что save() был вызван ровно 1 раз
        verify(calculationRepository, times(1)).save(any(Calculation.class));
    }

    // ЛОГИКА ТЕСТИРОВАНИЯ ОШИБОК:
    // Проверяем не только "счастливый путь", но и обработку ошибок
    @Test
    void testDivide_ByZero_ThrowsException() {
        //RANGE: не настраиваем заглушку - при ошибке save не должен вызываться

        //ACT & ASSERT В ОДНОМ ВЫРАЖЕНИИ:
        // assertThrows проверяет что метод бросит исключение
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculatorService.divide(10, 0) // Делим на ноль - должно бросить исключение
        );

        // ПРОВЕРКА СООБЩЕНИЯ ОБ ОШИБКЕ:
        // Важно проверять не только тип исключения, но и сообщение
        verify(calculationRepository, never()).save(any(Calculation.class));
    }

    // ЛОГИКА ТЕСТИРОВАНИЯ ИСТОРИИ:
    // Тестируем метод getCalculationHistory()
    @Test
    void testGetCalculationHistory() {
        // ARRANGE - ПОДГОТОВКА ТЕСТОВЫХ ДАННЫХ:
        // Создаем список "фейковых" записей которые вернет заглушка
        List<Calculation> mockCalculations = Arrays.asList(
                new Calculation("add", "2 + 3", 5),
                new Calculation("subtract", "5 - 2", 3)
        );

        // Устанавливаем ID вручную так как это сделала бы база
        mockCalculations.get(0).setId(1L);
        mockCalculations.get(1).setId(2L);

        // НАСТРОЙКА ЗАГЛУШКИ:
        // Когда вызовут findAll() - возвращаем наш тестовый список
        when(calculationRepository.findAll()).thenReturn(mockCalculations);

        // ACT - ВЫЗОВ МЕТОДА:
        List<CalculationHistoryDTO> result = calculatorService.getCalculationHistory();

        // ASSERT - ПРОВЕРКА РЕЗУЛЬТАТА:
        assertEquals(2, result.size(), "Должно быть 2 записи в истории");
        assertEquals(1L, result.get(0).getId(), "Первая запись должна иметь ID=1");
        assertEquals("add", result.get(0).getOperation(), "Первая операция должна быть 'add'");
        assertEquals(5, result.get(0).getResult(), "Результат первой операции должен быть 5");

        // ПРОВЕРКА ВЫЗОВА ЗАГЛУШКИ:
        verify(calculationRepository, times(1)).findAll();
    }
}

