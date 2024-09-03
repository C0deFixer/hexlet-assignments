package exercise.validator;

import exercise.model.BaseEntity;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UtilValidator {
    @Autowired
    private Validator validator;

    public void validate(BaseEntity be) {
        Set<ConstraintViolation<BaseEntity>> violations = validator.validate(be);
        if (!violations.isEmpty()) {
            String errors = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                    .toString();
            throw new ConstraintViolationException("Error occurred: " + errors, violations);

        }
    }

}
