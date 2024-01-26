package exercise;

import java.math.BigDecimal;
import java.math.RoundingMode;

// BEGIN
public class Circle {
    private final Point center;
    private final int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {

        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        } else {
            if (radius < 10) {
                BigDecimal bd = BigDecimal.valueOf(radius * radius * 3.14159);
                bd = bd.setScale(0, RoundingMode.HALF_UP);
                return bd.doubleValue();
            }
            else return radius * radius * 3.14159;


        }

    }
}

// END
