package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.BookService;
import Objects.BookTitleDto;
import Objects.Response;

public class SearchByTitleCommand implements ICommand {
    private final BookService bookService;

    public SearchByTitleCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof BookTitleDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        var title=  ((BookTitleDto) dto).getTitle();
        String message = String.format("Books containing '%s' in their title:", title);
        return new Response(true,message,
                bookService.searchBooksByTitle(title));
    }

    @Override
    public Class<?> getDtoClass() {
        return BookTitleDto.class;
    }

}
