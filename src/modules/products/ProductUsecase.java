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

    public void createProduct(Product product) throws Exception {
        if (product.name.equals("")) {
            throw new Exception("Product name cannot be empty");
        }

        if (product.price <= 0) {
            throw new Exception("Product price cannot be empty");
        }

        this.repo.createProduct(product);
    }
}
