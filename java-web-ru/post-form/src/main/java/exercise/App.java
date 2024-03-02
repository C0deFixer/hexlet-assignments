package exercise;

import io.javalin.Javalin;
import java.util.List;
import java.util.Collections;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var firstName = StringUtils.capitalize(ctx.formParam("firstName"));
            var lastName = StringUtils.capitalize(ctx.formParam("lastName"));
            var email = ctx.formParamAsClass("email", String.class).getOrDefault("").trim().toLowerCase();
            String password = ctx.formParamAsClass("password",String.class).getOrDefault("").trim();
            String  passwordConfirmation = ctx.formParamAsClass("passwordConfirmation", String.class).getOrDefault("").trim();
            if (!password.equals(passwordConfirmation)) {
                throw new NotFoundResponse("password confirmation was wrong!");
            }
            UserRepository.save(new User(firstName, lastName, email, password));
            ctx.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
