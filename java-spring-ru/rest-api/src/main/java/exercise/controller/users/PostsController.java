package exercise.controller.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    private static List<Post> posts = Data.getPosts();

    @GetMapping(path = "/users/{userId}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> index(@PathVariable Integer userId,
                            @RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream()
                .filter(p -> p.getUserId() == userId)
                .skip((page - 1) * limit)
                .limit(limit)
                .toList();

    }

    @PostMapping(path = "/users/{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post index(@PathVariable Integer userId,
                      @RequestBody Post post) {
        Post result = new Post();
        result.setUserId(userId);
        result.setSlug(post.getSlug());
        result.setTitle(post.getTitle());
        result.setBody(post.getBody());;
        posts.add(result);
        return result;
    }

}
// END
