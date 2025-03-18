package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.BookService;
import Objects.GenreDto;
import Objects.Response;

public class SearchByGenreCommand implements ICommand {
    private final BookService bookService;

    public SearchByGenreCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof GenreDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        var genre=  ((GenreDto) dto).getGenre();
        String message = String.format("Books containing '%s' in genre:", genre);

        return new Response(true,message,
                bookService.searchBooksByGenre(genre));
    }

    @Override
    public Class<?> getDtoClass() {
        return GenreDto.class;
    }

}
