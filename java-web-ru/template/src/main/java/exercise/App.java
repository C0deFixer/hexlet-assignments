package exercise;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;

import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;

import java.util.Collections;
import java.util.Optional;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN

        app.get("/users", ctx -> {
            UsersPage usersPage = getUsersPage();
            ctx.render("users/index.jte", Collections.singletonMap("page", usersPage));
        });
        app.get("/users/{id}", ctx -> {
            var userID = ctx.pathParamAsClass("id", Long.class).getOrDefault((long) -1);

            User user = findUser(userID).orElseThrow(() -> new NotFoundResponse("User not found"));
            //будем считать что user это и есть DAO
            UserPage userPage = new UserPage(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
            ctx.render("users/show.jte", Collections.singletonMap("page", userPage));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static Optional<User> findUser(long userID) {
        return USERS.stream()
                .filter(x -> x.getId() == userID)
                .findFirst();
    }

    public static UsersPage getUsersPage() {
        return new UsersPage(USERS);
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
