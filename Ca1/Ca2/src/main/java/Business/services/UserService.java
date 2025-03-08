package Business.services;

import Business.exeptions.ShopException;
import Business.models.*;
import Repositorys.AuthorRepository;
import Repositorys.UserRepository;

import java.util.Optional;

import static Business.exeptions.ShopException.ErrorType.*;

public class UserService {
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    public UserService(UserRepository userRepository, AuthorRepository authorRepository) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author,String username) {
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();
        if (!(person instanceof Admin)){
            throw new ShopException(USER_NOT_ADMIN);
        }
        boolean Exist = authorRepository.existsByUsername(author.getName());
        if (Exist) {
            throw new ShopException(AUTHOR_ALREADY_EXISTS);
        }
        authorRepository.saveAuthor(author);
    }

    public void addUser(Person person) {
        boolean Exist = userRepository.existsByUsernameAndEmail(person.getUsername(), person.getEmail());
        if (Exist) {
            throw new ShopException(USER_ALREADY_EXISTS);
        }
        userRepository.saveUser(person);
    }

    public void addCredit(Transaction transaction) {
        Optional<Person> person = userRepository.findByUsername(transaction.username());
        if (transaction.credit() < 1000) {
            throw new IllegalArgumentException("credit must be at least 1000");

        }
        if (person.isEmpty()) {
            throw new IllegalArgumentException("user not found");
        }
        Person p = person.get();
        if (!(p instanceof Customer customer)) {
            throw new IllegalArgumentException("user not found");
        }
        customer.addCredit(transaction.credit());

    }

    public UserDetails getUserDetails(String username) {
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new ShopException(USER_NOT_FOUND);
        }
        Person person = personOpt.get();

        String role;
        if (person instanceof Customer) {
            role = "Customer";
            return new UserDetails(
                    person.getUsername(),
                    role,
                    person.getEmail(),
                    person.getAddress(),
                    ((Customer) person).getWalletBalance()
            );
        } else if (person instanceof Admin) {
            role = "Admin";
            return new UserDetails(
                    person.getUsername(),
                    role,
                    person.getEmail(),
                    person.getAddress()
            );
        } else {
            role ="Person";
            return new UserDetails(
                    person.getUsername(),
                    role,
                    person.getEmail(),
                    person.getAddress()
            );
        }


    }

    public Author getAuthorDetails(String username) {
        Optional<Author> authorOptional = authorRepository.findByUsername(username);
        if (authorOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        return authorOptional.get();

    }

    public bookContent getContent(String username,String title){
        Optional<Person> personOpt = userRepository.findByUsername(username);
        if (personOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Person person = personOpt.get();
        if (!(person instanceof Customer customer)){
            throw new IllegalArgumentException("User not found");
        }
        Optional<Book> availableBookByTitle = customer.findAvailableBookByTitle(title);
        if (availableBookByTitle.isEmpty()){
            throw new IllegalArgumentException("User not found");
        }
        return new bookContent(availableBookByTitle.get().getTitle(),availableBookByTitle.get().getContent());


    }




    public record bookContent(String title,String content){}
}
