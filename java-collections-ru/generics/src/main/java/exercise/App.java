package exercise;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
//import java.util.HashMap;

// BEGIN
public class App {
    public static void main(String[] args) {
     /*   List<Map<String, String>> books = new ArrayList<>();

        Map<String, String> book1 = new HashMap<>(Map.of("title", "Cymbeline", "author", "Shakespeare", "year", "1611"));
        Map<String, String> book2 = new HashMap<>(
                Map.of("title", "Book of Fooos", "author", "FooBar", "year", "1111")
        );
        Map<String, String> book3 = new HashMap<>(
                Map.of("title", "The Tempest", "author", "Shakespeare", "year", "1611")
        );
        Map<String, String> book4 = new HashMap<>(
                Map.of("title", "Book of Foos Barrrs", "author", "FooBar", "year", "2222")
        );
        Map<String, String> book5 = new HashMap<>(
                Map.of("title", "Still foooing", "author", "FooBar", "year", "3333")
        );

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        Map<String, String> where = new HashMap<>(Map.of("author", "Shakespeare", "year", "1611"));

        List<Map<String, String>> result = App.findWhere(books, where);

        System.out.println(result);*/
    }

    public static <M extends Map<String, String>> List<M> findWhere(List<M> books, M where) {
        List<M> result = new ArrayList<>();
        boolean match;
        for (M book : books) {
            match = true;
            for (Map.Entry<String, String> bookField : where.entrySet()) {
                if (!book.containsKey(bookField.getKey()) || !book.get(bookField.getKey()).equals(bookField.getValue())) {
                    match = false;
                }
            }
            if (match) {
                result.add(book);
            }
        }
        return result;
    }

}
//END
