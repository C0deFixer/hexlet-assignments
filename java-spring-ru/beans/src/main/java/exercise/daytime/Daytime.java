package exercise.daytime;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

// Интерфейс содержит метод, возвращающий название времени суток
// Реализация методов представлена в классах Morning, Day, Evening, Night,
// которые реализуют этот интерфейс
public interface Daytime {

    String getName();
}
