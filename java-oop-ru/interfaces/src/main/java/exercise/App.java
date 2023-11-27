package exercise;

import java.util.ArrayList;
import java.util.List;

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
        result.stream().forEach(System.out::println);


    }

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        List<String> result = apartments.stream()
                .sorted(Home::compareTo)
                .map(x -> x.toString())
                // .peek(System.out::println)
                .limit(count)
                .toList();
        return result;

    }
}

// END
