package exercise.controller;

import java.util.Collections;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;

import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.validation.ValidationException;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        String userSessionId = ctx.sessionAttribute("userId");
        var user = userSessionId == null ? null : UsersRepository.find(Long.valueOf(userSessionId));
        MainPage page = new MainPage(user);
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        LoginPage lp = new LoginPage("","");
        ctx.render("build.jte", Collections.singletonMap("page", lp));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Имя должно быть длинее 2ух символов")
                    .get();
            var user = UsersRepository.findByName(name);
            var pass = ctx.formParamAsClass("password", String.class).get();
            if (user != null && user.getPassword().equals(encrypt(pass))) {
                ctx.sessionAttribute("userId", String.valueOf(user.getId()));
                ctx.redirect("/");
            } else {
                LoginPage lp = new LoginPage(name, "Wrong username or password");
                ctx.render("build.jte", Collections.singletonMap("page", lp));
            }
        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            LoginPage lp = new LoginPage(name, e.getErrors().toString());
            ctx.render("build.jte", Collections.singletonMap("page", lp));

        }
    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("userId", null);
        ctx.redirect("/");
    }

    // END
}
