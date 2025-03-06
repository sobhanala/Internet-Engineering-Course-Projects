package Business.commands;

import Business.ICommand;
import Business.services.BookService;
import Business.models.Book;
import Infrastucure.Mapper;
import Objects.BookDto;
import Objects.Response;

public class AddBookCommand implements ICommand {
    private final BookService bookService;

    public AddBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof BookDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        Book book  = Mapper.toBookObject((BookDto) dto);
         bookService.addBook(book);
        return new Response(true,"Removed book from cart.","");
    }

    @Override
    public Class<?> getDtoClass() {
        return BookDto.class;
    }
}

