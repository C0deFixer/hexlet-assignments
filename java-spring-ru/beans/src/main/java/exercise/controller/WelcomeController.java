package exercise.controller;

import exercise.Application;
import exercise.daytime.Day;
import exercise.daytime.Daytime;
import exercise.daytime.Night;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Objects;

// BEGIN
@RestController
@RequestMapping("/welcome")
class WelcomeController {

    @Autowired
    private Daytime daytime; //should be initialized correctly on request

/*    @Autowired
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


    @GetMapping
    public String get() {
        Objects.requireNonNull(daytime,"Bean Datime was not initialized Correctly!");
        return String.format("It is %s now! Welcome to Spring!", this.daytime.getName());

    }

    @PostConstruct
    public void init() {
        System.out.println("Bean " + this.getClass().getCanonicalName() + " is initialized!");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up " + this.getClass().getCanonicalName() + " resources or performing final actions!");
    }
}
// END
