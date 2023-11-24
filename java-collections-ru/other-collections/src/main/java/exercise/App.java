package exercise;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// BEGIN
public class App{
    public static void main(String[] args) {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("two", "own");
        data1.put("one", "buh");
        data1.put("three", 4);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("one", "eon");
        data2.put("two", "own");
        data2.put("three", 5);
        data2.put("four", 5);
        System.out.println(genDiff(data1, data2));
    }
    public static LinkedHashMap<String, String> genDiff(Map<String,Object> m1, Map<String,Object> m2){

        Set<String> keys = new HashSet<>();
        //Set<String> keys2 = new HashSet<>();
        keys.addAll(m1.keySet());
        keys.addAll(m2.keySet());
        LinkedHashMap<String, String> result = new LinkedHashMap<>();

        //System.out.println(keys);
        //Stream.concat(Stream.of(m1.keySet()), Stream.of(m2.keySet()));
        //LinkedHashMap<String,Object> result = Stream.concat(Stream.of(m1), Stream.of(m2))
        //        .collect(Collectors.toCollection(LinkedHashMap::new));
        for (String key: keys){
            if (!m1.containsKey(key)) {
                result.put(key.toString(), "added");
            }
            else if (!m2.containsKey(key)) {
                result.put(key.toString(), "deleted");
            } else {
                result.put(key.toString(), m1.get(key).equals(m2.get(key)) ? "unchanged" : "changed");
            }
        }


       /* Stream.of(keys)
                .sorted()
                .peek(x -> {
                    if (!m1.containsKey(x)) {
                        result.put(x.toString(), "added");
                    }
                    if (!m2.containsKey(x)) {
                        result.put(x.toString(), "deleted");
                    } else {
                        result.put(x.toString(), m1.get(x).equals(m2.get(x)) ? "unchanged" : "changed");
                    }
                    );*/
                //collect(Col)
                //.flatMap(element -> Stream.of(element))
                //.forEach()
                //.forEach(System.out::println);
        //keys1.addAll(m2.keySet());

                    //System.out.println(result);
                    //return new LinkedHashMap<>(16);
                    return result;

    }

/*    public static void play(int tryCount, Supplier sup) {
        ArrayList<Integer> result = IntStream.generate(sup)
                .limit(tryCount)
                .sorted()
                .collect(Collectors.toList());


    }*/
}
//END
