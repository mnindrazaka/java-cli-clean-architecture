import java.sql.SQLException;
import java.util.Scanner;

import modules.databases.Database;
import modules.products.ProductHandler;
import modules.products.ProductRepo;
import modules.products.ProductUsecase;
import modules.transactions.TransactionHandler;
import modules.transactions.TransactionRepo;
import modules.transactions.TransactionUsecase;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner scanner = new Scanner(System.in);

        Database database = new Database("localhost:3306", "shop", "root", "roottoor");

        ProductRepo productRepo = new ProductRepo(database);
        ProductUsecase productUsecase = new ProductUsecase(productRepo);
        ProductHandler productHandler = new ProductHandler(productUsecase);

        TransactionRepo transactionRepo = new TransactionRepo(database);
        TransactionUsecase transactionUsecase = new TransactionUsecase(transactionRepo);
        TransactionHandler transactionHandler = new TransactionHandler(transactionUsecase, productUsecase);

        while (true) {
            System.out.println("Welcome to my shop");
            System.out.println("Menu :");
            System.out.println("1. Show Products");
            System.out.println("2. Create New Product");
            System.out.println("3. Show Transactions");
            System.out.println("4. Create Transaction");
            System.out.println("5. Exit");

            System.out.print("Enter your option : ");
            int option = scanner.nextInt();

            switch (option) {
                case 1: {
                    productHandler.showProducts();
                    break;
                }
                case 2: {
                    productHandler.createProduct();
                    break;
                }
                case 3: {
                    transactionHandler.showTransactions();
                    break;
                }
                case 4: {
                    transactionHandler.createTransaction();
                    break;
                }
                case 5: {
                    System.out.println("Bye bye");
                    break;
                }
                default: {
                    System.out.println("Input invalid");
                    break;
                }
            }

            if (option == 5) {
                break;
            }
        }

        scanner.close();
    }
}
