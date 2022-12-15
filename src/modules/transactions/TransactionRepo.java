package modules.transactions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modules.databases.Database;
import modules.products.Product;

public class TransactionRepo {
    private Database database;

    public TransactionRepo(Database database) {
        this.database = database;
    }

    public Transaction[] geTransactions() throws SQLException {
        ArrayList<Transaction> transactions = new ArrayList<>();

        ResultSet transactionsResultSet = this.database.executeQuery("SELECT * FROM transactions");

        while (transactionsResultSet.next()) {
            int id = transactionsResultSet.getInt("id");
            String customerName = transactionsResultSet.getString("customer_name");
            int money = transactionsResultSet.getInt("money");

            String query = "SELECT * FROM transaction_items INNER JOIN products ON transaction_items.product_id = products.id WHERE transaction_id = "
                    + id;
            ResultSet transactionItemsResultSet = this.database.executeQuery(query);

            ArrayList<TransactionItem> items = new ArrayList<>();

            while (transactionItemsResultSet.next()) {
                // TODO need to validate
                int transactionItemId = transactionItemsResultSet.getInt("id");
                int amount = transactionItemsResultSet.getInt("amount");

                int productId = transactionItemsResultSet.getInt("id");
                String productName = transactionItemsResultSet.getString("name");
                int productPrice = transactionItemsResultSet.getInt("price");

                Product product = new Product(productId, productName, productPrice);
                TransactionItem item = new TransactionItem(transactionItemId, product, amount);
                items.add(item);
            }

            Transaction transaction = new Transaction(id, customerName, money,
                    items.toArray(new TransactionItem[items.size()]));
            transactions.add(transaction);
        }

        return transactions.toArray(new Transaction[transactions.size()]);
    }

    public void createTransaction(Transaction transaction) throws SQLException {
        String query = String.format("INSERT INTO transactions SET customer_name='%s', money=%d",
                transaction.customerName, transaction.money);
        int transactionId = this.database.executeAndGetInsertedId(query);

        for (TransactionItem item : transaction.items) {
            String queryItem = String.format(
                    "INSERT INTO transaction_items SET amount=%d, product_id=%d, transaction_id=%d",
                    item.amount, item.product.id, transactionId);
            this.database.execute(queryItem);
        }
    }
}
