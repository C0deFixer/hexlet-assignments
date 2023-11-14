package exercise;

// BEGIN
public class Cottage  implements Home{
    private final double area;
    private final int florCount;

    public Cottage(double area, int florCount) {

        this.area = area;
        this.florCount = florCount;
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {
        double anotherArea = another.getArea();
        if ( anotherArea < this.area ) {
            return 1;
        }
        else if ( anotherArea > this.area ) {
            return -1;
        }
        else return 0;
    }

    @Override
    public String toString() {
        return florCount +
                " этажный коттедж площадью" +
                area +
                "метров";
    }
}
// END
