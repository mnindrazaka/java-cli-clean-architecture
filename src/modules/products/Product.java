package modules.products;

public class Product {
    public int id;
    public String name;
    public int price;

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void printInfo() {
        System.out.println("id \t: " + this.id);
        System.out.println("name \t: " + this.name);
        System.out.println("price \t: " + this.price);
    }
}
