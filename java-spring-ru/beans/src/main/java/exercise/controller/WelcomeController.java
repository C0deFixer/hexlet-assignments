package exercise.controller;

import exercise.Application;
import exercise.beanmanager.MyDayTimeManager;
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
    private MyDayTimeManager dayTimeManager;

    //@Autowired
    //private Daytime daytime; //альтернативное решение через RequestScope




    @GetMapping
    public String get() {
        Daytime daytime =  dayTimeManager.createBean();
        Objects.requireNonNull(daytime,"Bean Datime was not initialized Correctly!");
        return String.format("It is %s now bean version %s ! Welcome to Spring!", daytime.getName(), daytime.toString());

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
