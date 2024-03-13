package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts/index.jte";
    }
    public static String postPath() {
        return "/posts/show.jte";
    }
    public static String postPath(Long id) {
        return postPath(String.valueOf(id));
    }

    public static String postPath(String id) {
        return "/posts/"+id;
    }

    public static String showPath() {
        return "/posts/{id}";
    }

    // END
}
