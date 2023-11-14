package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {
    public static void main(String[] args) {
        List<Home> apartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildApartmentsList(apartments, 3);
        System.out.println(result);

    }

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        List<String> result = Stream.of(apartments)
                .sorted(Cx,y -> x.compareTo(y))
                .reduce()
                .map(Home::toString)
                .collect(Collectors.toList());
        return result;

    }
}

// END
