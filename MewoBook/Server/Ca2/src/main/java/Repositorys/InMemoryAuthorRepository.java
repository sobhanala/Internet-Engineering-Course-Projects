package Repositorys;

import Business.models.Author;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryAuthorRepository implements AuthorRepository {
    private final List<Author> authorList = new ArrayList<>();

    public InMemoryAuthorRepository() {
        authorList.add(new Author("defaultName", "defaultPenName", null, null));
    }

    @Override
    public Optional<Author> findByUsername(String username) {
        return authorList.stream()
                .filter(a -> a.getName().equals(username))
                .findAny();
    }

    @Override
    public void saveAuthor(Author author) {
        authorList.add(author);
    }

    @Override
    public boolean existsByUsername(String username) {
        return authorList.stream()
                .anyMatch(a -> a.getName().equals(username));
    }
}