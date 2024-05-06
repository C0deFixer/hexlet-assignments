package exercise;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "/posts")
    public List<Post> index(@RequestParam(defaultValue = "10") Integer limit, @RequestParam(defaultValue = "0") Integer page) {
        return posts.stream().skip(page * limit).limit(limit).toList();
    }

    @GetMapping(path = "/posts/{id}")
    public Optional<Post> show(@PathVariable String id){
        return posts.stream().filter(p  -> p.getId().equals(id)).findFirst();
    }

    @PostMapping(path = "/posts")
    public Post create(@RequestBody Post post){
        posts.add(post);
        return post;
    }

    @PutMapping(path = "/posts/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        Optional<Post> postFinded = posts.stream().filter(p -> p.getId().equals(id)).findFirst();
        if (postFinded.isPresent()) {
            var post = postFinded.get();
            post.setBody(data.getBody());
            post.setTitle(data.getTitle());
            data.setId(post.getId());
        }
        return data;
    }

    @DeleteMapping(path = "/posts/{id}")
    public void delete(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
    // END

}
