package exercise;

import io.javalin.Javalin;
import exercise.controller.UsersController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get(NamedRoutes.rootPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.post(NamedRoutes.usersPath(), UsersController::create); // -> POST "/users"
        // END

        app.get(NamedRoutes.userPath("{id}"), UsersController::show); // -> GET "/users/" + id;
        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start("127.0.0.1",8080);
    }
}
