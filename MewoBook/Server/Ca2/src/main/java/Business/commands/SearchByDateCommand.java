package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.BookService;
import Objects.DateDto;
import Objects.Response;

public class SearchByDateCommand implements ICommand {
    private final BookService bookService;

    public SearchByDateCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof DateDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        var from=  ((DateDto) dto).getFrom();
        var to =((DateDto) dto).getTo();
        String message = String.format("Books published from '%s' to '%s':", from,to);

        return new Response(true,"Purchase completed successfully",
                bookService.searchBooksByYear(from,to));
    }

    @Override
    public Class<?> getDtoClass() {
        return DateDto.class;
    }

}
