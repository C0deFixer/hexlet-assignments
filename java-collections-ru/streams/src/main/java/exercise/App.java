package exercise;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collector;

public class App {


// BEGIN
public static void main(String[]args){
    String[] emails = {
            "info@gmail.com",
            "info@yandex.ru",
            "mk@host.com",
            "support@hexlet.io",
            "info@hotmail.com",
            "support.yandex.ru@host.com"
    };

    List<String> emailsList = Arrays.asList(emails);
    List<String> domains = List.of("gmail.com", "yandex.ru", "hotmail.com");
/*    emailsList.stream()
            .filter(email -> !email.isBlank())
            .filter(email -> email.lastIndexOf("@") > 0 && email.lastIndexOf("@") < email.length()-1)
            .map(email -> email.substring(email.lastIndexOf("@") + 1))

            .filter(domain -> domains.contains(domain))
            /.forEach(System.out::println);*/
    //.count();
    System.out.println(App.getCountOfFreeEmails(emailsList)); // 3

}

    public static int getCountOfFreeEmails(List<String> emails) {
        List<String> domains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return (int) emails.stream()
                .filter(email -> !email.isBlank())
                .filter(email -> email.lastIndexOf("@") > 0 && email.lastIndexOf("@") < email.length() - 1)
                .map(email -> email.substring(email.lastIndexOf("@") + 1))
                .filter(domain -> domains.contains(domain))
                .count();
        //.collect(Collector.counting());;
    }
// END
}