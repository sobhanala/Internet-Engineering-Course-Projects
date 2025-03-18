package Repositorys;

import Business.models.Author;
import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> findByUsername(String username);
    void saveAuthor(Author author);
    boolean existsByUsername(String username);
}