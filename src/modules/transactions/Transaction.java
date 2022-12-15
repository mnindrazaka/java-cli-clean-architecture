package modules.transactions;

public class Transaction {
    public int id;
    public String customerName;
    public int money;
    public TransactionItem[] items;

    public Transaction(int id, String customerName, int money, TransactionItem[] items) {
        this.id = id;
        this.customerName = customerName;
        this.money = money;
        this.items = items;
    }

    public Transaction(TransactionItem[] items) {
        this.items = items;
    }

    public int getTotal() {
        int total = 0;
        for (TransactionItem item : this.items)
            total += item.getSubtotal();
        return total;
    }

    public int getChange() {
        return this.money - this.getTotal();
    }

    public void printInfo() {
        System.out.println("Customer Name : " + this.customerName);
        System.out.println("Product List : ");

        for (TransactionItem item : this.items) {
            System.out.println(item.product.name + " : Rp." + item.product.price + " x " + item.amount
                    + " = Rp." + item.getSubtotal());
        }

        System.out.println("Total : Rp." + this.getTotal());
        System.out.println("Money : Rp." + this.money);
        System.out.println("Change : Rp." + this.getChange());
    }
}
