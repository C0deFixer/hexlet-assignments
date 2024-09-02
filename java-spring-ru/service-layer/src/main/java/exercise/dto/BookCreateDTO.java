package exercise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookCreateDTO {
    @NotNull(message = "Author id must be provided")
    private Long authorId;

    @NotBlank(message = "Title must be filled")
    private String title;
}
