package Business.models;

import java.util.List;

public record BookReviewShow(String title, List<Review> reviews, double meanScore){};
