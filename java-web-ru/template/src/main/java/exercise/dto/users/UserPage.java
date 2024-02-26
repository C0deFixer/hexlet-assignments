package exercise.dto.users;

import exercise.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@AllArgsConstructor
@Getter
public class UserPage {
    final long id;
    final String firstName;
    final String lastName;
    final String email;

}

// END
