package exercise.daytime;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Day implements Daytime {
    private static int counter = 0;
    private final int id;

    public Day() {
        id = ++counter;
    }

    private final String name = "day";

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
    // END


    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
