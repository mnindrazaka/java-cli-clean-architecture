package modules.transactions;

import modules.products.Product;

public class TransactionItem {
    public int id;
    public Product product;
    public int amount;

    public TransactionItem(int id, Product product, int amount) {
        this.id = id;
        this.product = product;
        this.amount = amount;
    }

    public TransactionItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public int getSubtotal() {
        return this.product.price * this.amount;
    }
}
