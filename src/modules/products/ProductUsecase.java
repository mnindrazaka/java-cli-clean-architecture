package modules.products;

import java.sql.SQLException;

public class ProductUsecase {
    private ProductRepo repo;

    public ProductUsecase(ProductRepo repo) {
        this.repo = repo;
    }

    public Product[] geProducts() throws SQLException {
        return this.repo.geProducts();
    }

    public void createProduct(Product product) throws SQLException {
        this.repo.createProduct(product);
    }
}
