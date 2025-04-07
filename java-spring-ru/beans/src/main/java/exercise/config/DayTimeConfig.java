package exercise.config;

import exercise.beanmanager.MyDayTimeManager;
import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class DayTimeConfig {

    //Альтернативное решение
    /*
    @Bean
    @RequestScope
    private void setDayTime(Integer hour) {
            if (hour < 6 || hour > 22) {
                //return new Night();
                daytime = new Night();
            }
         else  {daytime = new Day();
         }
            //else return new Day();
        }*/

    @Bean
    //@Scope("prototype")
    public Daytime getDayTime() {
        System.out.println("*** call  for  getDayTime ***");
        //LocalDateTime time = LocalDateTime.now(ZoneId.of("UTC-"+6));
        LocalDateTime time = LocalDateTime.now();
        if (time.getHour() < 6 || time.getHour() > 22) {
            return new Night();
        } else return new Day();
    }

    @Bean
    //По сути это реализация Абстрактной фабрики
    public MyDayTimeManager dayTimeManager() {
        return new MyDayTimeManager() {
            @Override //тут мы переопределяем поведение
            public Daytime createBean() {
                //тут (со Слов Жени Борисова) обращение не к Методу как таковому, а к Конструктору Бина public Daytime getDayTime()
                //скорее всего это связано с тем что Spring следит за тем что возвращает метод и будет либо проваливаться и создавать новый экзепляр
                //или singleton отдавать уже готовый.
                System.out.println("*** day time manager call to prepare new bean");
                return getDayTime();
            }
        };
    }
}
