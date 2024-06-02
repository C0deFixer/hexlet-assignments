package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;


// BEGIN
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Bean
    @RequestScope
    public Daytime getDayTime() {
        //LocalDateTime time = LocalDateTime.now(ZoneId.of("UTC-"+6));
        LocalDateTime time = LocalDateTime.now();
        if (time.getHour() < 6 || time.getHour() > 22) {
            return new Night();
        }
        else return new Day();
    }
    // END
}
