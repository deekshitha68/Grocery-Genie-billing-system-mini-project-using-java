
import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String name;
    double price;
    int stock;

    public Item(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

class CartItem {
    Item item;
    int quantity;

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

public class Main{

    static ArrayList<Item> availableItems = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        initializeItems();

        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    displayAvailableItems();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    generateBill();
                    break;
                case 5:
                    System.out.println("Thank you for shopping with us!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void initializeItems() {
        availableItems.add(new Item("Bread", 35, 50));
        availableItems.add(new Item("Milk", 25, 30));
        availableItems.add(new Item("Eggs", 70, 100));
        availableItems.add(new Item("Butter", 150, 40));
        availableItems.add(new Item("Cheese", 200, 20));
        availableItems.add(new Item("Apples", 120, 60));
        availableItems.add(new Item("Bananas", 50, 100));
        availableItems.add(new Item("Chicken", 250, 30));
        availableItems.add(new Item("Fish", 300, 25));
        availableItems.add(new Item("Rice", 80, 200));
        availableItems.add(new Item("Wheat Flour", 45, 150));
        availableItems.add(new Item("Sugar", 40, 100));
        availableItems.add(new Item("Salt", 20, 200));
        availableItems.add(new Item("Pepper", 30, 50));
        availableItems.add(new Item("Tomatoes", 60, 80));
        availableItems.add(new Item("Potatoes", 30, 120));
        availableItems.add(new Item("Onions", 40, 100));
        availableItems.add(new Item("Garlic", 120, 60));
        availableItems.add(new Item("Ginger", 80, 40));
        availableItems.add(new Item("Carrots", 50, 70));
    }

    static void showMenu() {
        System.out.println("\n*** Supermarket Billing System ***");
        System.out.println("1. Display available items");
        System.out.println("2. Add items to cart");
        System.out.println("3. View cart");
        System.out.println("4. Generate bill");
        System.out.println("5. Exit");
    }

    static int getUserChoice() {
        System.out.print("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    static void displayAvailableItems() {
        System.out.println("\nAvailable Items:");
        for (Item item : availableItems) {
            System.out.println(item.name + " - RS" + item.price + " (Stock: " + item.stock + ")");
        }
    }

    static void addToCart() {
        displayAvailableItems();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the item to add to cart: ");
        String itemName = scanner.next();

        Item selectedItem = findItemByName(itemName);
        if (selectedItem != null) {
            System.out.print("Enter the quantity: ");
            int quantity = scanner.nextInt();

            if (quantity > 0 && quantity <= selectedItem.stock) {
                CartItem cartItem = new CartItem(selectedItem, quantity);
                cart.add(cartItem);
                selectedItem.stock -= quantity;

                System.out.println(quantity + " " + itemName + "(s) added to the cart.");
            } else {
                System.out.println("Invalid quantity or insufficient stock. Please try again.");
            }
        } else {
            System.out.println("Item not found. Please enter a valid item name.");
        }
    }

    static Item findItemByName(String itemName) {
        for (Item item : availableItems) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("\nItems in Cart:");
            for (CartItem cartItem : cart) {
                System.out.println(cartItem.item.name + " - RS" + cartItem.item.price + " x " + cartItem.quantity);
            }
        }
    }

    static void generateBill() {
        double totalBill = 0.0;

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. No bill generated.");
        } else {
            System.out.println("\n*** Bill ***");
            for (CartItem cartItem : cart) {
                double itemTotal = cartItem.item.price * cartItem.quantity;
                System.out.println(cartItem.item.name + " - RS" + cartItem.item.price + " x " + cartItem.quantity
                        + " = RS" + itemTotal);
                totalBill += itemTotal;
            }
            System.out.println("Total: RS" + totalBill);

            // Allow multiple payment options (Cash, Credit Card, Digital Wallet)
            System.out.println("Choose payment option:");
            System.out.println("1. Cash");
            System.out.println("2. Credit Card");
            System.out.println("3. Digital Wallet");

            int paymentChoice = getUserChoice();
            processPayment(paymentChoice, totalBill);

            cart.clear();
        }
    }

    static void processPayment(int paymentChoice, double totalBill) {
        switch (paymentChoice) {
            case 1:
                System.out.println("Payment successful. Thank you!");
                break;
            case 2:
                System.out.println("Credit card payment successful. Thank you!");
                break;
            case 3:
                System.out.println("Digital wallet payment successful. Thank you!");
                break;
            default:
                System.out.println("Invalid payment option.");
        }
    }
}
