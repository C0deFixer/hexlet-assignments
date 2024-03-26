package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
    public static void save(Product product) throws SQLException {
        String sql = "INSERT INTO products (title, price) VALUES (?,?)";
        try (var conn = dataSource.getConnection();
             var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getTitle());
            ps.setInt(2, product.getPrice());
            ps.executeUpdate();
            var keys = ps.getGeneratedKeys();
            if (keys.next()) {
                product.setId(keys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }

        }
    }

    public static Optional<Product> find(Long id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (var conn = dataSource.getConnection();
             var st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            st.executeQuery();
            var resultSet = st.getResultSet();
            if (resultSet.next()) {
                Product product = new Product(resultSet.getString("title"), resultSet.getInt("price"));
                product.setId(resultSet.getLong("id"));
                return Optional.of(product);
            } else {
                return Optional.empty();
            }
        }

    }

    public static List<Product> getEntities() throws SQLException {
        String sql = "SELECT * FROM products LIMIT 1000";
        List<Product> result = new ArrayList<>();
        try (var conn = dataSource.getConnection();
             var st = conn.prepareStatement(sql)) {
            st.executeQuery();
            var resultSet = st.getResultSet();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("title"), resultSet.getInt("price"));
                product.setId(resultSet.getLong("id"));
                result.add(product);
            }
        }
        return result;
    }
    // END
}
