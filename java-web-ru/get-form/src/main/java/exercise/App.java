package exercise;

import io.javalin.Javalin;

import java.util.Arrays;
import java.util.List;
import exercise.model.User;
import exercise.dto.users.UsersPage;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term = ctx.queryParamAsClass("term", String.class).getOrDefault("");
            //ИМХО логику фильтрации нужно делать внутри класса UsersPage
            UsersPage uP = new UsersPage(USERS,term);
            ctx.render("users/index.jte", Collections.singletonMap("page",uP));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start("127.0.0.1",8080);
    }

}
