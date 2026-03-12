import java.util.*;

// ================= DATA CLASSES =================
class FoodItem {
    String name;
    double price;
    String category;

    FoodItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
}

class CartItem {
    FoodItem item;
    int quantity;

    CartItem(FoodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

class Review {
    String name;
    int rating;
    String comment;

    Review(String name, int rating, String comment) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }
}

// ================= MAIN APPLICATION =================
public class  OnileRestaurantEMenuRealm {

    static List<FoodItem> menu = new ArrayList<>();
    static List<CartItem> cart = new ArrayList<>();
    static List<Review> reviews = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void initializeMenu() {
        menu.add(new FoodItem("Hyderabadi Veg Biryani", 120, "Veg"));
        menu.add(new FoodItem("Mysore Masala Dosa", 80, "Veg"));
        menu.add(new FoodItem("Paneer Butter Masala", 150, "Veg"));
        menu.add(new FoodItem("Chicken Biryani", 180, "Non-Veg"));
        menu.add(new FoodItem("Mutton Rogan Josh", 350, "Non-Veg"));
        menu.add(new FoodItem("Mango Lassi", 70, "Dessert"));
        menu.add(new FoodItem("Gulab Jamun", 60, "Dessert"));

        reviews.add(new Review("Rahul", 5, "The Biryani was amazing!"));
    }

    // ================= MENU DISPLAY =================
    public static void showMenu() {
        System.out.println("\n----- MENU -----");

        for (int i = 0; i < menu.size(); i++) {
            FoodItem f = menu.get(i);
            System.out.println((i + 1) + ". " + f.name + " | ₹" + f.price + " | " + f.category);
        }

        System.out.print("Select item number: ");
        int choice = sc.nextInt();

        if (choice >= 1 && choice <= menu.size()) {
            System.out.print("Enter quantity: ");
            int qty = sc.nextInt();
            addToCart(menu.get(choice - 1), qty);
        }
    }

    // ================= ADD TO CART =================
    public static void addToCart(FoodItem item, int qty) {

        for (CartItem ci : cart) {
            if (ci.item.name.equals(item.name)) {
                ci.quantity += qty;
                System.out.println("Updated quantity in cart.");
                return;
            }
        }

        cart.add(new CartItem(item, qty));
        System.out.println(item.name + " added to cart.");
    }

    // ================= VIEW CART =================
    public static void viewCart() {

        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        double total = 0;

        System.out.println("\n----- YOUR CART -----");

        for (int i = 0; i < cart.size(); i++) {
            CartItem ci = cart.get(i);
            double subtotal = ci.item.price * ci.quantity;
            total += subtotal;

            System.out.println((i + 1) + ". " + ci.item.name +
                    " x" + ci.quantity +
                    " = ₹" + subtotal);
        }

        System.out.println("Total: ₹" + total);
    }

    // ================= REMOVE ITEM =================
    public static void removeItem() {

        viewCart();

        if (cart.isEmpty()) return;

        System.out.print("Enter item number to remove: ");
        int index = sc.nextInt() - 1;

        if (index >= 0 && index < cart.size()) {
            cart.remove(index);
            System.out.println("Item removed.");
        }
    }

    // ================= PLACE ORDER =================
    public static void placeOrder() {

        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        double total = 0;

        for (CartItem ci : cart) {
            total += ci.item.price * ci.quantity;
        }

        System.out.println("Order placed successfully!");
        System.out.println("Total Bill: ₹" + total);

        cart.clear();
    }

    // ================= ADD REVIEW =================
    public static void addReview() {

        sc.nextLine();

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Rating (1-5): ");
        int rating = sc.nextInt();
        sc.nextLine();

        System.out.print("Comment: ");
        String comment = sc.nextLine();

        reviews.add(new Review(name, rating, comment));

        System.out.println("Review submitted!");
    }

    // ================= SHOW REVIEWS =================
    public static void showReviews() {

        System.out.println("\n----- CUSTOMER REVIEWS -----");

        for (Review r : reviews) {
            System.out.println(r.name + " | Rating: " + r.rating + "⭐");
            System.out.println(r.comment);
            System.out.println("---------------------");
        }
    }

    // ================= MAIN METHOD =================
    public static void main(String[] args) {

        initializeMenu();

        int choice;

        do {
            System.out.println("\n====== TASTY BITES ======");
            System.out.println("1. View Menu");
            System.out.println("2. View Cart");
            System.out.println("3. Remove Item from Cart");
            System.out.println("4. Place Order");
            System.out.println("5. Add Review");
            System.out.println("6. Show Reviews");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showMenu();
                    break;

                case 2:
                    viewCart();
                    break;

                case 3:
                    removeItem();
                    break;

                case 4:
                    placeOrder();
                    break;

                case 5:
                    addReview();
                    break;

                case 6:
                    showReviews();
                    break;

                case 0:
                    System.out.println("Thank you for visiting Tasty Bites!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);
    }
}