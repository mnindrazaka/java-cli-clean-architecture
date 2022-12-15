package modules.products;

import java.util.Scanner;

public class ProductHandler {
    private ProductUsecase usecase;
    private Scanner scanner;

    public ProductHandler(ProductUsecase usecase) {
        this.usecase = usecase;
        this.scanner = new Scanner(System.in);
    }

    public void showProducts() {
        System.out.println("Product List : ");
        try {
            for (Product product : this.usecase.geProducts()) {
                product.printInfo();
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println("Show products failed : " + e.getMessage());
        }

    }

    public void createProduct() {
        System.out.print("Enter product name : ");
        String name = scanner.nextLine();

        System.out.print("Enter product price : ");
        int price = scanner.nextInt();

        Product product = new Product(name, price);

        try {
            this.usecase.createProduct(product);
        } catch (Exception e) {
            System.out.println("Create product failed : " + e.getMessage());
        }
    }
}
