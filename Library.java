package LibraryMangementSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void start() {
        while(true) {
            System.out.println("\n--- Library Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Add Student");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. View All Books");
            System.out.println("6. View All Students");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch(choice) {
                case 1: addBook(); break;
                case 2: addStudent(); break;
                case 3: issueBook(); break;
                case 4: returnBook(); break;
                case 5: viewBooks(); break;
                case 6: viewStudents(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully!");
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        students.add(new Student(id, name));
        System.out.println("Student added successfully!");
    }

    private void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int bookId = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt(); sc.nextLine();

        Book book = findBook(bookId);
        if(book == null) { System.out.println("Book not found!"); return; }
        if(book.isIssued()) { System.out.println("Book already issued!"); return; }

        Student student = findStudent(studentId);
        if(student == null) { System.out.println("Student not found!"); return; }

        book.setIssued(true);
        System.out.println("Book issued to " + student.getName());
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int bookId = sc.nextInt(); sc.nextLine();

        Book book = findBook(bookId);
        if(book == null) { System.out.println("Book not found!"); return; }
        if(!book.isIssued()) { System.out.println("Book was not issued!"); return; }

        book.setIssued(false);
        System.out.println("Book returned successfully!");
    }

    private void viewBooks() {
        if(books.isEmpty()) { System.out.println("No books available."); return; }
        System.out.println("--- Books List ---");
        for(Book b : books) System.out.println(b);
    }

    private void viewStudents() {
        if(students.isEmpty()) { System.out.println("No students registered."); return; }
        System.out.println("--- Students List ---");
        for(Student s : students) System.out.println(s);
    }

    private Book findBook(int id) {
        for(Book b : books) if(b.getId() == id) return b;
        return null;
    }

    private Student findStudent(int id) {
        for(Student s : students) if(s.getId() == id) return s;
        return null;
    }
}