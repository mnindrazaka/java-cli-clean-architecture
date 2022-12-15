package modules.transactions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modules.products.Product;
import modules.products.ProductUsecase;

public class TransactionHandler {
    private TransactionUsecase transactionUsecase;
    private ProductUsecase productUsecase;
    private Scanner scanner;

    public TransactionHandler(TransactionUsecase transactionUsecase, ProductUsecase productUsecase) {
        this.transactionUsecase = transactionUsecase;
        this.productUsecase = productUsecase;
        this.scanner = new Scanner(System.in);
    }

    public void showTransactions() {
        System.out.println("Transaction List : ");
        try {
            for (Transaction transaction : this.transactionUsecase.geTransactions()) {
                transaction.printInfo();
                System.out.println("");
            }
        } catch (SQLException e) {
            System.out.println("Failed to show transactions : " + e.getMessage());
        }
    }

    public void createTransaction() {
        try {
            Product[] products = productUsecase.geProducts();

            ArrayList<TransactionItem> items = new ArrayList<>();

            while (true) {
                System.out.println("Product List : ");
                int i = 1;
                for (Product product : products) {
                    System.out.println(i + ". " + product.name + " : Rp." + product.price);
                    i++;
                }

                System.out.print("Enter your option : ");
                int productOption = scanner.nextInt();

                Product product = products[productOption - 1];

                System.out.print("Enter amount : ");
                int amount = scanner.nextInt();
                scanner.nextLine();

                TransactionItem item = new TransactionItem(product, amount);
                items.add(item);

                System.out.print("Do you want to add product again ? (y/n)");
                String answer = scanner.nextLine();

                if (!answer.equalsIgnoreCase("y")) {
                    break;
                }
            }

            Transaction transaction = new Transaction(items.toArray(new TransactionItem[items.size()]));

            System.out.println("The total is : Rp." + transaction.getTotal());

            System.out.print("Enter customer name : ");
            transaction.customerName = scanner.nextLine();

            System.out.print("Enter customer money : ");
            transaction.money = scanner.nextInt();

            System.out.println("The change is Rp." + transaction.getChange());

            this.transactionUsecase.createTransaction(transaction);
        } catch (SQLException e) {
            System.out.println("Failed to create transaction : " + e.getMessage());
        }
    }
}
