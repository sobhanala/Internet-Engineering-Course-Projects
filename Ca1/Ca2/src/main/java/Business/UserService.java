package Business;

import Business.models.Author;
import Business.models.Person;
import Repositorys.AuthorRepository;
import Repositorys.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final AuthorRepository authorRepository;

    public UserService(UserRepository userRepository, AuthorRepository authorRepository) {
        this.userRepository = userRepository;
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
        boolean Exist = authorRepository.existsByUsername(author.getUsername());
        if (Exist) {
            throw new IllegalArgumentException("Author already exists");
        }
        authorRepository.saveAuthor(author);
    }    public void addUser(Person person) {
        boolean Exist = userRepository.existsByUsernameAndEmail(person.getUsername(), person.getEmail());
        if (Exist) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.saveUser(person);
    }}