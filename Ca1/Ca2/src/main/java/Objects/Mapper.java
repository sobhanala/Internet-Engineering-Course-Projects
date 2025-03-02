package Objects;

import Business.models.*;

public class Mapper {

    public static Person toPersonObject(PersonDto personDto) {
        if (personDto == null || personDto.getRole() == null) {
            throw new IllegalArgumentException("Invalid person data or role is missing.");
        }

        return switch (personDto.getRole().toLowerCase()) {
            case "admin" -> new Admin(
                    personDto.getUserName(),
                    personDto.getPassword(),
                    personDto.getEmail(),
                    toAddress(personDto.getAddress())
            );
            case "customer" -> new Customer(
                    personDto.getUserName(),
                    personDto.getPassword(),
                    personDto.getEmail(),
                    toAddress(personDto.getAddress())
            );
            default -> throw new IllegalArgumentException("Unsupported role: " + personDto.getRole());
        };
    }
    public static Book toBookObject(BookDto bookDto){
        if (bookDto == null) {
            throw new IllegalArgumentException("Invalid book data.");
        }
        return new Book(
                bookDto.getUsername(),
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getPublisher(),
                bookDto.getYear(),
                bookDto.getPrice(),
                bookDto.getSynopsis(),
                bookDto.getContent(),
                bookDto.getGenres()
        );

    }
    public static Author toAuthorObject(AuthorDto authorDto){
        if (authorDto == null) {
            throw new IllegalArgumentException("Invalid author data.");
        }
        return new Author(
                authorDto.getUsername(),
                authorDto.getName(),
                authorDto.getPenName(),
                authorDto.getBorn()
        );
    }

    private static Address toAddress(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }
        return new Address(
                addressDto.getCity(),
                addressDto.getCountry()
        );
    }
}
