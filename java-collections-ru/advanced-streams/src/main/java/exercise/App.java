package exercise;

/*import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;*/
import java.util.stream.Collectors;
//import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App{
    static final String PREF_ENVIRONMENT = "environment";
    static final String PREF = "X_FORWARDED_";
    public static void main(String[] args) throws Exception {

/*        String content =  readFixture("s2.conf");
        String result = App.getForwardedVariables(content);
        System.out.println(result);*/

    }
/*    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }
    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }*/
    public static String getForwardedVariables(String fileContent) {
        //String result;
        String result = Stream.of(fileContent)
                .map(x -> x.split("\n"))
                .flatMap(element -> Stream.of(element))
                .filter(x -> x.startsWith(PREF_ENVIRONMENT))
                .map(x -> x.substring(PREF_ENVIRONMENT.length()+2,x.length()-1))
                .map(x -> x.split(","))
                .flatMap(element -> Stream.of(element))
                .filter(x -> x.startsWith(PREF))
                .map(x -> x.substring(PREF.length()))
                .collect(Collectors.joining(",","",""));
        //.forEach(element ->  System.out.println(element));
        return result;

    }
}
//END
