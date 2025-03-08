package Repositorys;

import Business.models.Review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryReviewRepository implements ReviewRepository {
    private final List<Review> reviews = new ArrayList<>();

    @Override
    public void saveReview(Review review) {
        reviews.add(review);
    }



    @Override
    public Optional<Review> findReviewByUserAndBook(String username, String bookTitle) {
        return reviews.stream()
                .filter(r -> r.getPerson().getUsername().equals(username) && r.getBook().getTitle().equals(bookTitle))
                .findAny();
    }

    @Override
    public List<Review> findReviewsByBookTitle(String bookTitle) {
        return reviews.stream()
                .filter(r -> r.getBook().getTitle().equals(bookTitle))
                .collect(Collectors.toList());
    }
}
