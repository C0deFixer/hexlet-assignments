package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", String.class).getOrDefault("-1");

            Optional<Map<String, String>> company = COMPANIES.stream()
                    .filter(x -> x.get("id").equals(id))
                    .findFirst();
            if (company.isEmpty()) {
                throw new NotFoundResponse("Company not found");
            }
            ctx.json(company.get());
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7080);
    }
}
