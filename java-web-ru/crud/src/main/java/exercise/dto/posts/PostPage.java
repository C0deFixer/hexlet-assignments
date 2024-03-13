package exercise.dto.posts;

import exercise.model.Post;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class PostPage {
    private String name;
    private String body;
    private Map<String, List<ValidationError<Object>>> errors;

    public PostPage (Post post) {
        this.name = post.getName();
        this.body = post.getBody();
    }

    public PostPage (String name, String body) {
        this.name = name;
        this.body = body;
    }
    public PostPage (String name, String body, Map<String, List<ValidationError<Object>>> errors) {
        this.name = name;
        this.body = body;
        this.errors = errors;
    }
}
