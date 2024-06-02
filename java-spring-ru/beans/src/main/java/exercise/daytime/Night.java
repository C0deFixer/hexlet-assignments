package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void init() {
        System.out.println("Bean "+this.name+" is initialized!");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning up "+this.name+" resources or performing final actions!");
    }
    // END
}
