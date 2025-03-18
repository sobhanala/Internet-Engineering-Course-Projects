package Repositorys;

import Business.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    void saveReview(Review review);
    Optional<Review> findReviewByUserAndBook(String username, String bookTitle);
    List<Review> findReviewsByBookTitle(String bookTitle);
}