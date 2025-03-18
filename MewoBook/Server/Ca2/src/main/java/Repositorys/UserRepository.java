package Repositorys;

import Business.models.Person;
import java.util.Optional;

public interface UserRepository {
    Optional<Person> findByUsername(String username);
    void saveUser(Person person);
    boolean existsByUsernameAndEmail(String username, String email);
}