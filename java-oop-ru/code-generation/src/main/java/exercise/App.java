package exercise;


import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {

    public static void save(Path path, Car car) throws Exception {
        Files.write(path, car.serialize().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
    }

    public static Car extract(Path path) throws Exception {
        String jsonRepresentation = Files.readString(path);
        return Car.unserialize(jsonRepresentation);
    }

}
// END
