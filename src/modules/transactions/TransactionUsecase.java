package modules.transactions;

import java.sql.SQLException;

public class TransactionUsecase {
    private TransactionRepo repo;

    public TransactionUsecase(TransactionRepo repo) {
        this.repo = repo;
    }

    public Transaction[] geTransactions() throws SQLException {
        return this.repo.geTransactions();
    }

    public void createTransaction(Transaction transaction) throws Exception {
        if (transaction.customerName.equals("")) {
            throw new Exception("Customer name cannot be empty");
        }

        if (transaction.items.length <= 0) {
            throw new Exception("Transaction items cannot be empty");
        }

        if (transaction.money <= 0) {
            throw new Exception("Money cannot be empty");
        }

        this.repo.createTransaction(transaction);
    }
}
