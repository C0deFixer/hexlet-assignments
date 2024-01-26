package exercise;

// BEGIN
public class App {
    public static void main(String[] args) {

    }

    public static void printSquare(Circle circle) {
        try {
            if (circle.getRadius() >= 10 ){
                System.out.println(circle.getSquare());
            }
            else {
                System.out.println((int) circle.getSquare());
            }
        } catch (NegativeRadiusException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }
    }
}
// END
