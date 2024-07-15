package exercise.dto;

import lombok.Getter;
import lombok.Setter;

// BEGIN

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Getter
@Setter
public class GuestCreateDTO {

    @NotBlank
    private String name;

    @Email
    private String email;
    // BEGIN

    @Pattern(regexp = "^\\+{1}[\\d]{11,13}$")
    private String phoneNumber;

    @Pattern(regexp = "^[\\d]{4}$")
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}
// END
