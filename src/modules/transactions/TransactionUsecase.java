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

    public void createTransaction(Transaction transaction) throws SQLException {
        this.repo.createTransaction(transaction);
    }
}
