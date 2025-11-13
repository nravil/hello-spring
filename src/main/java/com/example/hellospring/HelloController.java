package com.example.hellospring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private CalculationRepository calculationRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return "ПРИВЕТ! Мое первое Spring приложение работает! 🚀";
    }

    @GetMapping("/info")
    public String getInfo() {
        return """
                <h1>🚀 Мое первое Spring приложение!</h1>
                <p><b>Статус:</b> РАБОТАЕТ!</b>
                <p><b>Дата:</b> %s</p>
                <p><b>Время:</b> %s</p>
                <hr>
                <p>Ты создал работающее веб-приложение!</p>
                """.formatted(
                java.time.LocalDate.now(),
                java.time.LocalTime.now()
        );
    }

    @GetMapping("/about")
    public String aboutMe() {
        return """
                <h1>Обо мне</h1>
                <p>Имя: [Твоё имя]</p>
                <p>Цель: Стать разработчиком</p>
                <p>Сегодня я изучил Spring Boot!</p>
                """;
    }

    //добавил метод сложения
    @GetMapping("/calc/add")
    public String addNumbers(
            @RequestParam int a,
            @RequestParam int b
    ) {
        int result = a + b;

        Calculation calculation = new Calculation("add", a + " + " + b, result);
        calculationRepository.save(calculation);
        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Сложение</p>
                <p><b>Число A:</b> %d</p>
                <p><b>Число B:</b> %d</p>
                <p><b>Результат:</b> %d + %d = <span style='color: green; font-size: 24px;'>%d</span></p>
                <p style='color: gray;'>✅ Результат сохранен в базу данных (ID: %d)</p>
                <hr>
                <a href='/history'>📊 Посмотреть историю</a> |\s
                <a href='/calculator'>🧮 Главная калькулятора</a>
                """.formatted(a, b, a, b, result, calculation.getId());
    }

    // добавил метод вычитания
    @GetMapping("/calc/subtract")
    public String subtractNumbers(
            @RequestParam int x,
            @RequestParam int y
    ) {
        int result = x - y;
        Calculation calculation = new Calculation("subtract", x + " - " + y, result);
        calculationRepository.save(calculation);
        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Вычитание</p>
                <p><b>Число X:</b> %d</p>
                <p><b>Число Y:</b> %d</p>
                <p><b>Результат:</b> %d - %d = <span style='color: blue; font-size: 24px;'>%d</span></p>
                <p style='color: gray;'>✅ Результат сохранен в базу данных (ID: %d)</p>
                <hr>
                <a href='/history'>📊 Посмотреть историю</a> |\s
                <a href='/calculator'>🧮 Главная калькулятора</a>
                """.formatted(x, y, x, y, result, calculation.getId());
    }

    // добавил метод умноженя
    @GetMapping("/calc/multiply")
    public String multiplyNumbers(
            @RequestParam double factor1,
            @RequestParam double factor2
    ) {
        double result = factor1 * factor2;
        Calculation calculation = new Calculation("multiply", factor1 + "*" + factor2, result);
        calculationRepository.save(calculation);
        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Умножение</p>
                <p><b>Число первый множитель:</b> %.2f</p>
                <p><b>Число второй множитель:</b> %.2f</p>
                <p><b>Результат:</b> %.2f * %.2f = <span style='color: red; font-size: 24px;'>%.2f</span></p>
                <p style='color: gray;'>✅ Результат сохранен в базу данных (ID: %d)</p>
                <hr>
                <a href='/history'>📊 Посмотреть историю</a> |\s
                <a href='/calculator'>🧮 Главная калькулятора</a>
                """.formatted(factor1, factor2, factor1, factor2, result, calculation.getId());
    }

    // добавил метод деления
    @GetMapping("/calc/divide")
    public String divideNumbers(
            @RequestParam double numerator,
            @RequestParam double denominator
    ) {
        double result = numerator / denominator;
        if (denominator == 0) {
            return "Ошибка. Делить на 0 нельзя";
        }
        Calculation calculation = new Calculation("divide", numerator + "/" + denominator, result);
        calculationRepository.save(calculation);

        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Деление</p>
                <p><b>Числитель:</b> %.2f</p>
                <p><b>Знаменатель:</b> %.2f</p>
                <p><b>Результат:</b> %.2f / %.2f = <span style='color: red; font-size: 24px;'>%.2f</span></p>
                <p style='color: gray;'>✅ Результат сохранен в базу данных (ID: %d)</p>
                <hr>
                <a href='/history'>📊 Посмотреть историю</a> |\s
                <a href='/calculator'>🧮 Главная калькулятора</a>
                """.formatted(numerator, denominator, numerator, denominator, result, calculation.getId());
    }

    @GetMapping("/calculator")
    public String calculatorHome() {
        return """
                <!DOCTYPE html>
                <html>
                <head>
                    <title>🧮 Мой калькулятор</title>
                    <style>
                        body { font-family: Arial; margin: 40px; }
                        .menu { background: #f0f0f0; padding: 20px; border-radius: 10px; }
                        a { display: block; margin: 10px 0; text-decoration: none; color: #0066cc; }
                        a:hover { color: #004499; }
                    </style>
                </head>
                <body>
                    <h1>🧮 Добро пожаловать в калькулятор!</h1>
                    <div class='menu'>
                        <h3>Быстрые ссылки:</h3>
                        <a href='/calc/add-form'>➕ Сложение (форма)</a>
                        <a href='/calc/add?a=15&b=25'>📊 15 + 25 = ?</a>
                        <a href='/calc/subtract?x=50&y=17'>📊 50 - 17 = ?</a>
                        <a href='/calc/multiply?factor1=123&factor2=456'>📊 123 * 456 = ?</a>
                        <a href='/calc/divide?numerator=5&denominator=6'>📊 5 / 6 = ?</a>
                        <a href='/info'>ℹ️ Информация о приложении</a>
                        <a href='/hello'>👋 Приветствие</a>
                    </div>
                
                    <h3>Или введи свои числа:</h3>
                    <p>Сложение: <code>/calc/add?a=ЧИСЛО&b=ЧИСЛО</code></p>
                    <p>Вычитание: <code>/calc/subtract?x=ЧИСЛО&y=ЧИСЛО</code></p>
                    <p>Умножение: <code>/calc/multiply?factor1=ЧИСЛО&factor2=ЧИСЛО</code></p>
                    <p>Деление: <code>/calc/divide?numerator=ЧИСЛО&denominator=ЧИСЛО</code></p>
                </body>
                </html>
                """;
    }

    @GetMapping("/history")
    public String showHistory() {
        List<Calculation> calculations = calculationRepository.findAll();

        //Проверяем есть ли данные
        if (calculations.isEmpty()) {
            return """
                    <h1>📊 История вычислений</h1>
                    <p>История пуста. Сделайте несколько вычислений!</p>
                    <a href='/calculator'>🧮 Перейти к калькулятору</a>
                    """;
        }
        //Создаем HTML таблицу для отображения
        StringBuilder historyTable = new StringBuilder();
        historyTable.append("""
                <h1>📊 История вычислений</h1>
                <table border='1' style='border-collapse: collapse; width: 100%;'>
                    <tr style='background-color: #f0f0f0;'>
                        <th>ID</th>
                        <th>Операция</th>
                        <th>Выражение</th>
                        <th>Результат</th>
                        <th>Время</th>
                    </tr>
                """);
        //Проходим по всем записям и добавляем в таблицу
        for (Calculation calc : calculations) {
            historyTable.append(String.format("""
                            <tr>
                                <td>%d</td>
                                <td>%s</td>
                                <td>%s</td>
                                <td><b>%.2f</b></td>
                                <td>%s</td>
                            </tr>
                            """, calc.getId(), calc.getOperation(), calc.getExpression(),
                    calc.getResult(), calc.getTimestamp()));
        }
        historyTable.append("""
                </table>
                <hr>
                <a href='/calculator'>🧮 Главная калькулятора</a>
                """);

        return historyTable.toString();
    }
    @GetMapping("/calc/add-form")
    public String showAddForm() {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <title>Сложение</title>
            <style>
                body { font-family: Arial; margin: 40px; }
                .form-group { margin: 15px 0; }
                label { display: inline-block; width: 100px; }
                input { padding: 8px; width: 200px; }
                button { padding: 10px 20px; background: #0066cc; color: white; border: none; cursor: pointer; }
                button:hover { background: #004499; }
            </style>
        </head>
        <body>
            <h1>🧮 Сложение чисел</h1>
            <form action="/calc/add" method="GET">
                <div class="form-group">
                    <label for="a">Число A:</label>
                    <input type="number" id="a" name="a" required>
                </div>
                <div class="form-group">
                    <label for="b">Число B:</label>
                    <input type="number" id="b" name="b" required>
                </div>
                <button type="submit">➗ Посчитать</button>
            </form>
            <hr>
            <a href='/calculator'>📊 Назад к калькулятору</a>
        </body>
        </html>
        """;
    }
}
