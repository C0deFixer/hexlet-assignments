package exercise;

import io.javalin.Javalin;
import exercise.controller.PostsController;
import exercise.controller.RootController;
import exercise.util.NamedRoutes;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get(NamedRoutes.rootPath(), RootController::index);

        // BEGIN
        app.get(NamedRoutes.postsPath(), PostsController::index);
        app.get(NamedRoutes.showPath(), PostsController::show);
        //app.get(NamedRoutes.postPath(), PostsController::show);
        app.post(NamedRoutes.postsPath(), PostsController::create);
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start("127.0.0.1", 8080);
    }
}
