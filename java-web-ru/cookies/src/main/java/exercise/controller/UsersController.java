package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import java.util.Collections;

import exercise.repository.UserRepository;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void index(Context ctx) throws Exception {
        ctx.render("index.jte");
    }

    public static void create(Context ctx) throws Exception {
        String firstName = ctx.formParamAsClass("firstName", String.class).get();
        String lastName = ctx.formParamAsClass("lastName", String.class).get();
        String email = ctx.formParamAsClass("email", String.class).get().trim().toLowerCase();
        String password = ctx.formParamAsClass("password", String.class).get().toLowerCase().trim();

        String token = Security.generateToken();
        ctx.cookie("token", token);
        User user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        ctx.redirect("/users/"+Long.toString(user.getId()));
    }

    public static void show(Context ctx) throws Exception {
        Long id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        String token = String.valueOf(ctx.cookie("token"));
        if (StringUtils.equals(user.getToken(), token)) {
            ctx.render("users/show.jte", Collections.singletonMap("user", user));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }


    }
    // END
}
