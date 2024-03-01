package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        ObjectMapper ob = new ObjectMapper();
        String stringCarJson = ob.writeValueAsString(this);
        return stringCarJson;
    }

    public static Car unserialize(String jsonRepresentation) throws IOException {
        ObjectMapper ob = new ObjectMapper();
        Car car = ob.readValue(jsonRepresentation, Car.class);
        return car;
    }

    // END
}
