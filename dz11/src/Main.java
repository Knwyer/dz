import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

enum BookStatus {
    AVAILABLE,
    RENTED
}

class Book {
    private String title;
    private String author;
    private String isbn;
    private BookStatus status;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = BookStatus.AVAILABLE;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ISBN: %s) - %s", title, author, isbn, status);
    }
}

class Reader {
    private String name;
    private List<Book> rentedBooks;

    public Reader(String name) {
        this.name = name;
        this.rentedBooks = new ArrayList<>();
    }

    public boolean rentBook(Book book) {
        if (rentedBooks.size() >= 3) {
            System.out.println(name + " cannot rent more than 3 books.");
            return false;
        }
        if (book.getStatus() == BookStatus.RENTED) {
            System.out.println(book.getTitle() + " is already rented.");
            return false;
        }
        book.setStatus(BookStatus.RENTED);
        rentedBooks.add(book);
        System.out.println(name + " rented \"" + book.getTitle() + "\".");
        return true;
    }

    public void returnBook(Book book) {
        if (rentedBooks.remove(book)) {
            book.setStatus(BookStatus.AVAILABLE);
            System.out.println(name + " returned \"" + book.getTitle() + "\".");
        } else {
            System.out.println(name + " did not rent \"" + book.getTitle() + "\".");
        }
    }
}

class Librarian {
    private String name;

    public Librarian(String name) {
        this.name = name;
    }

    public void manageLibrary() {
        System.out.println(name + " is managing the library.");
    }
}

class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book \"" + book.getTitle() + "\" added to the library.");
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.getStatus() == BookStatus.AVAILABLE) {
                System.out.println(book);
            }
        }
    }

    public void displayAllBooks() {
        System.out.println("All Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Optional<Book> searchBook(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase())
                        || book.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .findFirst();
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Librarian librarian = new Librarian("Alice");

        // Adding books to the library
        library.addBook(new Book("Java Programming", "John Doe", "123456789"));
        library.addBook(new Book("Learn Python", "Jane Smith", "987654321"));
        library.addBook(new Book("C# Basics", "Alice Brown", "555555555"));

        // Display available books
        library.displayAvailableBooks();

        // Create a reader
        Reader reader = new Reader("Bob");

        // Reader rents a book
        library.searchBook("Java Programming").ifPresent(reader::rentBook);

        // Display available books after renting
        library.displayAvailableBooks();

        // Reader returns the book
        library.searchBook("Java Programming").ifPresent(reader::returnBook);

        // Display all books
        library.displayAllBooks();
    }
}
