package exercise.beanmanager;

import exercise.daytime.Daytime;
import org.springframework.beans.factory.annotation.Lookup;

public abstract class MyDayTimeManager {

    @Lookup
    public abstract Daytime createBean();
}
