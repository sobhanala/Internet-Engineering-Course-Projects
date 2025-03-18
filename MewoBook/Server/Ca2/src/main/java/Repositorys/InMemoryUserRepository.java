package Repositorys;

import Business.models.Address;
import Business.models.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final List<Person> usersList = new ArrayList<>();

    public InMemoryUserRepository() {
        usersList.add(new Person("defaultUser", "defaultEmail@gmail.com", "defaultEmail@gmail.com",
                new Address("defaultCountry", "defaultCity")));
    }

    @Override
    public Optional<Person> findByUsername(String username) {
        return usersList.stream()
                .filter(p -> p.getUsername().equals(username))
                .findAny();
    }

    @Override
    public void saveUser(Person person) {
        usersList.add(person);
    }

    @Override
    public boolean existsByUsernameAndEmail(String username, String email) {
        return usersList.stream()
                .anyMatch(p -> p.getUsername().equals(username) && p.getEmail().equals(email));
    }
}