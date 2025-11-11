package com.example.hellospring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

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
        return """
                <h1>🧮 Калькулятор</h1>
                        <p><b>Операция:</b> Сложение</p>
                        <p><b>Число A:</b> %d</p>
                        <p><b>Число B:</b> %d</p>
                        <p><b>Результат:</b> %d + %d = <span style='color: green; font-size: 24px;'>%d</span></p>
                                <hr>
                                <a href='/info'>Назад к информации</a> |\s
                                <a href='/hello'>Приветствие</a>
                """.formatted(a, b, a, b, result);
    }

    // добавил метод вычитания
    @GetMapping("/calc/subtract")
    public String subtractNumbers(
            @RequestParam int x,
            @RequestParam int y
    ) {
        int result = x - y;
        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Вычитание</p>
                <p><b>Число X:</b> %d</p>
                <p><b>Число Y:</b> %d</p>
                <p><b>Результат:</b> %d - %d = <span style='color: blue; font-size: 24px;'>%d</span></p>
                <hr>
                        <a href='/calc/add?a=10&b=5'>Пример сложения</a> |
                        <a href='/info'>Информация</a>
                """.formatted(x, y, x, y, result);
    }

    //добавил метод умноженя
    @GetMapping("/calc/multiply")
    public String multiplyNumbers(
            @RequestParam double factor1,
            @RequestParam double factor2
    ) {
        double result = factor1 * factor2;
        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Умножение</p>
                <p><b>Число первый множитель:</b> %.2f</p>
                <p><b>Число второй множитель:</b> %.2f</p>
                <p><b>Результат:</b> %.2f * %.2f = <span style='color: red; font-size: 24px;'>%.2f</span></p>
                                               <hr>
                                <a href='/info'>Назад к информации</a> |\s
                                <a href='/hello'>Приветствие</a>
                """.formatted(factor1, factor2, factor1, factor2, result);
    }

    @GetMapping("/calc/divide")
    public String divideNumbers(
        @RequestParam double numerator,
        @RequestParam double denominator
    ) {
        double result = numerator / denominator;
        if (numerator == 0 || denominator == 0) {
            return "0";
        }
        return """
                <h1>🧮 Калькулятор</h1>
                <p><b>Операция:</b> Деление</p>
                <p><b>Числитель:</b> %.2f</p>
                <p><b>Знаменатель:</b> %.2f</p>
                <p><b>Результат:</b> %.2f / %.2f = <span style='color: red; font-size: 24px;'>%.2f</span></p>
                                               <hr>
                                <a href='/info'>Назад к информации</a> |\s
                                <a href='/hello'>Приветствие</a>
                """.formatted(numerator,denominator,numerator,denominator,result);
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
}
