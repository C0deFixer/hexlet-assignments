package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// BEGIN
class App {
    static List<Character> getCharacterArrayListFromString(String charSet) {
        char[] charArray = charSet.toLowerCase().toCharArray();
        List<Character> characterArrayList = new ArrayList<>(charArray.length);
        for (int i = 0; i < charArray.length; i++) {
            characterArrayList.add(i, (Character) charArray[i]);
        }
        ;
        return characterArrayList;
    }

    public static boolean scrabble(String charSet, String word) {
        boolean result = true;
        List<Character> charSetList = getCharacterArrayListFromString(charSet);
        List<Character> wordCharList = getCharacterArrayListFromString(word);
        //dosn't work cause Arrays.asList method need Array of Character Obj not char
        // char[] charSetArray = charSet.toLowerCase().toCharArray();
        //char[] charWordSetArray = word.toLowerCase().toCharArray();
        //List<Character> charSetList = new ArrayList<Character>(Arrays.asList(charSetArray));
        //List<Character> wordCharList = new ArrayList<Character>(Arrays.asList(charWordSetArray));
        for (Character letterWord : wordCharList) {
            //int letterIndex = charSetList.indexOf(letterWord));
            if (!charSetList.remove(letterWord)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //scrabble(args[0], args[1]);
        //
        //System.out.print("Test");
        //System.out.print(scrabble("rkqodlw","world"));
    }


}
//END
