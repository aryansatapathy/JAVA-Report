import java.util.*;
class Product {
    int productId;
    String productName;
    double price;
    int quantity;
    Product(int productId, String productName, double price, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
    void displayProduct() {
        double total = price * quantity;
        System.out.println(productId + ". " +productName +" - Rs." + price +" x " + quantity +" = Rs." + total
        );
    }
}
class Cart {
    Product[] products = new Product[100];
    int count = 0;
    void addProduct(Product p) {
        products[count] = p;
        count++;
        System.out.println("Product added successfully!");
    }
    void removeProduct(int id) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].productId == id) {
                for (int j = i; j < count - 1; j++) {
                    products[j] = products[j + 1];
                }
                count--;
                found = true;
                System.out.println("Product removed successfully!");
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found!");
        }
    }
    void displayCart() {
        if (count == 0) {
            System.out.println("Cart is empty!");
            return;
        }
        System.out.println("\nProducts in Cart:");
        for (int i = 0; i < count; i++) {
            products[i].displayProduct();
        }
    }
    double calculateTotal() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].price * products[i].quantity;
        }
        return total;
    }
    void checkout() {
        if (count == 0) {
            System.out.println("Cart is empty! Cannot checkout.");
            return;
        }
        double total = calculateTotal();
        double discount = total * 0.10;
        double tax = (total - discount) * 0.05;
        double finalAmount = total - discount + tax;
        displayCart();
        System.out.println("\nTotal: Rs." + total);
        System.out.println("Discount (10%): Rs." + discount);
        System.out.println("Tax (18%): Rs." + tax);
        System.out.println("Final Amount: Rs." + finalAmount);
    }
}
public class Main {
    static Product[] getAvailableProducts() {
        Product[] products = new Product[4];
        products[0] = new Product(1, "Laptop", 50000, 0);
        products[1] = new Product(2, "Phone", 25000, 0);
        products[2] = new Product(3, "Headphones", 5000, 0);
        products[3] = new Product(4, "Smartwatch", 8000, 0);
        return products;
    }
    static void displayProductCatalog(Product[] products) {
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.length; i++) {
            System.out.println(
                products[i].productId + ". " +
                products[i].productName +
                " - Rs." + products[i].price
            );
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cart cart = new Cart();
        Product[] catalog = getAvailableProducts();
        while (true) {
            System.out.println("\n--- Shopping Cart Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    displayProductCatalog(catalog);
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    Product selected = null;
                    for (int i = 0; i < catalog.length; i++) {
                        if (catalog[i].productId == id) {
                            selected = catalog[i];
                            break;
                        }
                    }
                    if (selected == null) {
                        System.out.println("Invalid Product ID!");
                        break;
                    }
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    cart.addProduct(
                        new Product(
                            selected.productId,
                            selected.productName,
                            selected.price,
                            qty
                        )
                    );
                    break;
                case 2:
                    System.out.print("Enter Product ID to remove: ");
                    int id2 = sc.nextInt();
                    cart.removeProduct(id2);
                    break;
                case 3:
                    cart.displayCart();
                    break;
                case 4:
                    cart.checkout();
                    break;
                case 5:
                    System.out.println("Visit us again!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
