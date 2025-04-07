package exercise.daytime;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Night implements Daytime {

    private static int counter = 0;
    private final int id;

    public Night() {
        id = ++counter;
    }

    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Bean " + this.name + " number " + id + " is provided!");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up " + this.name + " resources or performing final actions!");
    }

    @Override
    public String toString() {
        return "Night{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // END
}
