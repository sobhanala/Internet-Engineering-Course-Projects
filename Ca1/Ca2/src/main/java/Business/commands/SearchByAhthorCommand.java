package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.BookService;
import Objects.AuthornameDto;
import Objects.Response;

public class SearchByAhthorCommand implements ICommand {
    private final BookService bookService;

    public SearchByAhthorCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof AuthornameDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        var name=  ((AuthornameDto) dto).getName();
        String message = String.format("Books by '%s':",name);

        return new Response(true,message,
                bookService.searchBooksByAuthor(name));
    }

    @Override
    public Class<?> getDtoClass() {
        return AuthornameDto.class;
    }

}
