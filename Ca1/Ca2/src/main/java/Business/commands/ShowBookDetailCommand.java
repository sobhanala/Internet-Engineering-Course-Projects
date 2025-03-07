package Business.commands;

import Business.ICommand;
import Business.services.BookService;
import Objects.BookTitleDto;
import Objects.Response;

public class ShowBookDetailCommand implements ICommand {
    private final BookService bookService;

    public ShowBookDetailCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof BookTitleDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        String bookTitle  = ((BookTitleDto) dto).getTitle();
        return new Response(true,"Removed book from cart."
                ,bookService.getBookDetail(bookTitle));
    }

    @Override
    public Class<?> getDtoClass() {
        return BookTitleDto.class;
    }
}

