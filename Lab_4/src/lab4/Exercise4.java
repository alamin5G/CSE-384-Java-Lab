
package lab4;

/**
 *
 * @author alami
 */
class Book {
    String title;
    String author;
    double price;

    Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    void display() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
    }
}

public class Exercise4 {
    public static void main(String[] args) {
        Book[] books = new Book[3];

        books[0] = new Book("Programming in Java", "Liang", 1000);
        books[1] = new Book("Intro to DBMS", "Gupta", 400);
        books[2] = new Book("Intro to MIS", "Hasan", 1000);

        for (Book book : books) {
            book.display();
            System.out.println();
        }
    }
}

