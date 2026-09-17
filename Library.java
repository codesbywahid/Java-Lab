import java.util.ArrayList;
import java.util.Scanner;
abstract class Item {
    private String id;
    private String title;
    public Item(String id, String title) {
        this.id = id;
        this.title = title;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public abstract void display();
}
class Book extends Item {
    private double price;
    public Book(String isbn, String title, double price) {
        super(isbn, title);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public void display() {
        System.out.println(
            "Book -> ISBN: " + getId() +
            ", Title: " + getTitle() +
            ", Price: $" + price
        );
    }
}
public class Library {
    private ArrayList<Item> items;
    public Library() {
        items = new ArrayList<>();
        items.add(new Book("ISBN-101", "Java Programming", 500));
        items.add(new Book("ISBN-102", "Data Structures", 650));
        items.add(new Book("ISBN-103", "Operating Systems", 700));
    }
    public void storeBook(String isbn, String title, double price) {
        for (Item i : items) {
            if (i.getId().equals(isbn)) {
                System.out.println("Book with ISBN " + isbn + " already exists!");
                return;
            }
        }
        items.add(new Book(isbn, title, price));
        System.out.println("Book added successfully!");
    }
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No items in the library.");
            return;
        }
        System.out.println("\n--- Library Items ---");
        for (Item i : items) {
            i.display(); 
        }
    }
    public void searchAndDisplay(String id) {
        for (Item i : items) {
            if (i.getId().equals(id)) {
                System.out.print("Found: ");
                i.display();
                return;
            }
        }
        System.out.println("No book found with ISBN " + id);
    }
    public void totalCost() {
        double total = 0;
        for (Item i : items) {
            if (i instanceof Book) {
                total += ((Book) i).getPrice();
            }
        }
        System.out.println("Total cost of all books: $" + total);
    }
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\n--- Library Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book by ISBN");
            System.out.println("4. Total Cost of Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    library.storeBook(isbn, title, price);
                    break;
                case 2:
                    library.displayItems();
                    break;
                case 3:
                    System.out.print("Enter ISBN to search: ");
                    String searchId = sc.nextLine();
                    library.searchAndDisplay(searchId);
                    break;
                case 4:
                    library.totalCost();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting library system.");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
        sc.close();
    }
}