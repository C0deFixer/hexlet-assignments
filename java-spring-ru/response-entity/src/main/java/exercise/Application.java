package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping (path = "/posts")
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        List<Post> result =  posts.stream().skip((page - 1) * limit).limit(limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(result);
    }

    @GetMapping (path = "/posts/{id}")
    public ResponseEntity<Post> show(@PathVariable String id) {
        Assert.notNull(id, "id must not be null");
        Optional<Post> post =  posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        ResponseEntity<Post> result = ResponseEntity.of(post);
        if (post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(post);
    }

    @PostMapping (path = "/posts")
    public ResponseEntity<Post> create(@RequestBody Post post) {
        post.setId(String.valueOf(posts.size() + 1));
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
        Optional<Post> postFinded = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (postFinded.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        var post4Update = postFinded.get();
        post4Update.setTitle(post.getTitle());
        post4Update.setBody(post.getBody());
        return ResponseEntity.ok()
                .body(post);
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
