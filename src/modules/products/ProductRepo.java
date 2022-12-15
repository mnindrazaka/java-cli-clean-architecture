package modules.products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.databases.Database;

public class ProductRepo {
    private Database database;

    public ProductRepo(Database database) {
        this.database = database;
    }

    public Product[] geProducts() throws SQLException {
        ResultSet resultSet = this.database.executeQuery("SELECT * FROM products");

        ArrayList<Product> products = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int price = resultSet.getInt("price");

            Product product = new Product(id, name, price);
            products.add(product);
        }

        return products.toArray(new Product[products.size()]);
    }

    public void createProduct(Product product) throws SQLException {
        String query = String.format("INSERT INTO products SET name='%s', price=%d", product.name, product.price);
        this.database.execute(query);
    }
}
