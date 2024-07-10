package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;


// BEGIN
@RestController
@RequestMapping("/posts")
class PostsController{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDTO get(@PathVariable long id) {
        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with id %s not found",id)));
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        List<Comment> comments = commentRepository.findByPostId(post.getId());
        var commentsDTO = comments.stream()
                .map(this::toCommentDTO)
                .toList();
        postDTO.setComments(commentsDTO);
        return postDTO;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDTO> index() {
        var posts = postRepository.findAll()
                .stream()
                .map(this::toPostDTO)
                .toList();
        return posts;
    }

    public PostDTO toPostDTO(Post post)  {
        PostDTO postDTO = new PostDTO();
         postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        return postDTO;
    }
    public CommentDTO toCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());
        return commentDTO;
    }
}
// END
