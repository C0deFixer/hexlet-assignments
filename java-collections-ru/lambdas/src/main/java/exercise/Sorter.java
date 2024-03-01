package exercise;

import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        //List<Map<String, LocalDate>> sortedUsers = new ArrayList<>(users.size());
        List<String> result = new ArrayList<>();
        if (users.size() == 0) {
            return result;
        }
        result = users.stream()
                //.map(user -> Map.of(user.get("name"),user.get("birthday").subSequence(0, 10)))
                .filter(u -> u.get("gender").equals("male"))
                .sorted((u1, u2) -> LocalDate.parse(u1.get("birthday").subSequence(0, 10))
                        .compareTo(LocalDate.parse(u2.get("birthday").subSequence(0, 10))))
//Comparator.comparing(Map::values))
                .map(u -> u.get("name"))
                .collect(Collectors.toList());
        //.forEach(System.out::println);
/*    for (Map<String, String> user : users) {
        if (user.containsKey("birthday")) {
            CharSequence birthdayChars = user.get("birthday").subSequence(0, 10);
            LocalDate birthday = LocalDate.parse(birthdayChars);
            sortedUsers.add(Map.of(user.get("name"), birthday));
        }
    }

    sortedUsers.stream().forEach(System.out::println);*/
        return result;
        //return List.of("Petya");
        //users.stream(user ->  user.)
    }
}
// END
