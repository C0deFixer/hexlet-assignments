package exercise.controller;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;

public class PostsController {

    // BEGIN
    public static void index(Context ctx) {
        Integer pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        List<Post> postsList = PostRepository.getEntities().stream()
                //.sorted((x, y) -> Long.compare(x.getId(), y.getId()))
                .skip((pageNumber - 1) * 5L)
                .limit(5).toList();
        int pagesCount = PostRepository.size() % PostRepository.POSTS_PER_PAGE == 0 ? PostRepository.size() / PostRepository.POSTS_PER_PAGE : PostRepository.size() / PostRepository.POSTS_PER_PAGE + 1;
        //c ( PostRepository.size() PostRepository.POSTS_PER_PAGE PostRepository.size()%PostRepository.POSTS_PER_PAGE > 0
        PostsPage page = new PostsPage(pageNumber, pagesCount, postsList);
        ctx.render("posts/index.jte", Collections.singletonMap("page", page));
    }


    public static void create(Context ctx) {

        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.trim().length() > 2, "Название поста должно быть длиннее двух символов")
                    .check(PostRepository::existsByName, "Пост с таким названием уже существует")
                    .get();
            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.trim().length() > 10, "")
                    .get();
            Post post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());
        } catch (ValidationException e) {
            var name = ctx.formParamAsClass("name", String.class)
                    .getOrDefault("");
            var body = ctx.formParamAsClass("body", String.class)
                    .getOrDefault("");
            //Post post = new Post(name, body);
            PostPage page = new PostPage(name, body, e.getErrors());
            ctx.render("posts/show.jte", Collections.singletonMap("page", page));
        }


    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", Collections.singletonMap("page", page));
    }

    // END
}
