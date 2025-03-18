package org.example.main;

import Business.models.*;
import Business.services.BookService;
import Business.services.ShopService;
import Business.services.UserService;
import Infrastucure.JsonParser;
import Repositorys.InMemoryAuthorRepository;
import Repositorys.InMemoryBookRepository;
import Repositorys.InMemoryReviewRepository;
import Repositorys.InMemoryUserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        InMemoryAuthorRepository authorRepository = new InMemoryAuthorRepository();
        InMemoryBookRepository bookRepository=new InMemoryBookRepository();
        InMemoryUserRepository userRepository=new InMemoryUserRepository();
        InMemoryReviewRepository reviewRepository=new InMemoryReviewRepository();

        userRepository.saveUser(new Admin("admin_user", "admin_pass", "admin@mail.com", new Address("Iran", "Tehran")));
        userRepository.saveUser(new Customer("customer_user", "cust_pass", "cust@mail.com", new Address("Iran", "Karaj")));
        try {
            authorRepository.saveAuthor(new Author("sample name", "pen", new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1970"), null));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        bookRepository.saveBook(new Book("book title", "sample name", "pub", 2020, 100, "syn", "cont", List.of("fiction")));


        new CommandRunner(new JsonParser(), new ShopService(userRepository,bookRepository)
                ,new BookService(bookRepository,userRepository,reviewRepository),new UserService(userRepository,authorRepository)).start();
    }
}