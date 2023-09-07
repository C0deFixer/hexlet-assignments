package exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

// BEGIN
public class App {
public static void main(String[]args){
    /*Map<String, String> mp = new HashMap<>();
    Map<String, String> lhm = new LinkedHashMap<>();
    mp.put("test", "code");
    System.out.println(mp);*/
    Map<String, Integer> hmp = getWordCount("word text cat apple word map apple word");
    System.out.println(hmp);
    System.out.println(toString(hmp));
        }

    public static Map<String, Integer> getWordCount(String sentence) {
        String[] words = sentence.trim().split(" ");
        Map<String, Integer> resultMap = new HashMap<String, Integer>(words.length);
        for (String word : words) {
            if (word.length() != 0 && resultMap.containsKey(word)) {
                int oldvalue = resultMap.get(word).intValue();
                resultMap.put(word, Integer.valueOf((oldvalue + 1)));
            } else {
                resultMap.put(word, 1);
            }
        }
        return resultMap;
        //return Map.copyOf(resultMap);
    }

    public static String toString(Map<String, Integer> hmp) {
        if (hmp.isEmpty()) {
            return "{}";
        } ;
        StringJoiner result = new StringJoiner("");
        result.add("{");
        for (Map.Entry node : hmp.entrySet()) {
            if (node.getValue() == null) {
                continue;
            }
            result.add("\n  " + node.getKey() + ": " + node.getValue());
        }
        result.add(result.length() == 1 ? "}" : "\n}");
        return result.toString();
    }
}
//END
