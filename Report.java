import java.util.ArrayList;
class Product {
    int productId;
    String productName;
    double price;
    Product(int productId, String productName, double price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotal() {
        return product.price * quantity;
    }
}

class ShoppingCart {
    ArrayList<CartItem> cartItems;

    ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    void addItem(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.product.getProductId() == product.getProductId()) {
                item.quantity += quantity;
                System.out.println("Updated quantity: " + product.getProductName());
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
        System.out.println("Added: " + product.getProductName());
    }

    void removeItem(int productId) {
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).product.getProductId() == productId) {
                System.out.println("Removed: " + cartItems.get(i).product.getProductName());
                cartItems.remove(i);
                return;
            }
        }
        System.out.println("Product not found!");
    }

    void displayCart() {
        if (cartItems.isEmpty()) {
            System.out.println("\n--- Cart is Empty ---\n");
            return;
        }
        System.out.println("\n=== SHOPPING CART ===");
        double cartTotal = 0;
        for (CartItem item : cartItems) {
            double itemTotal = item.getTotal();
            System.out.println(item.product.getProductId() + ". " + item.product.getProductName() 
                    + " - Rs." + item.product.getPrice() + " x " + item.quantity + " = Rs." + itemTotal);
            cartTotal += itemTotal;
        }
        System.out.println("-------------------");
        System.out.println("Total: Rs." + cartTotal);
        System.out.println("===================\n");
    }

    double getCartTotal() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getTotal();
        }
        return total;
    }

    void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Cannot checkout - Cart is empty!");
            return;
        }
        System.out.println("\nCheckout Successful! Total Amount: Rs." + getCartTotal());
        cartItems.clear();
    }
}

public class Report {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Create products
        Product p1 = new Product(1, "Laptop", 50000);
        Product p2 = new Product(2, "Phone", 20000);
        Product p3 = new Product(3, "Headphones", 5000);

        // Add items to cart
        cart.addItem(p1, 1);
        cart.addItem(p2, 2);
        cart.addItem(p3, 1);

        cart.displayCart();

        // Add duplicate product
        cart.addItem(p2, 1);

        cart.displayCart();

        // Remove item
        cart.removeItem(3);

        cart.displayCart();

        // Checkout
        cart.checkout();
        cart.displayCart();
    }
}

