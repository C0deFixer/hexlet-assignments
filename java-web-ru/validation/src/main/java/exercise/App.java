package exercise;

import io.javalin.Javalin;
import io.javalin.validation.JavalinValidation;
import io.javalin.validation.ValidationError;
import io.javalin.validation.ValidationException;
import io.javalin.validation.Validator;

import java.util.List;

import exercise.model.Article;
import exercise.dto.articles.ArticlesPage;
import exercise.dto.articles.BuildArticlePage;

import java.util.Collections;
import java.util.Map;

import exercise.repository.ArticleRepository;


public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/articles", ctx -> {
            List<Article> articles = ArticleRepository.getEntities();
            var page = new ArticlesPage(articles);
            ctx.render("articles/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN

        app.get("/articles/build", ctx -> {
            var page = new BuildArticlePage();
            ctx.render("articles/build.jte", Collections.singletonMap("page", page));
        });

        app.post("/articles", ctx -> {
            Validator<String> titleValidator = ctx.formParamAsClass("title", String.class)
                    .check(value -> value.length() > 2, "Название не должно быть короче двух символов")
                    .check(ArticleRepository::notExistsByTitle, "Статья с таким названием уже существует");
            Validator<String> contentValidator = ctx.formParamAsClass("content", String.class)
                    .check(value -> value.length() > 10, "Статья должна быть не короче 10 символов");
            Map<String, List<ValidationError<?>>> errorsMap = JavalinValidation.collectErrors(titleValidator, contentValidator);
            try {
                var title = titleValidator.get();
                var content = contentValidator.get();
                var article = new Article(title, content);
                ArticleRepository.save(article);
                ctx.status(302);
                ctx.redirect("/articles");
            } catch (ValidationException e) {
                var title = ctx.formParam("title");
                var content = ctx.formParam("content");
                var page = new BuildArticlePage(title, content, e.getErrors());
                ctx.status(422);
                ctx.render("articles/build.jte", Collections.singletonMap("page", page));
            }
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        //app.start("127.0.0.1", 8080);
        app.start(7070);
    }
}
