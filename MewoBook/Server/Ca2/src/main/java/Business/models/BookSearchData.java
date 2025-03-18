package Business.models;

import java.util.List;

public record BookSearchData(
        String search,
        List<Book> books
) {}