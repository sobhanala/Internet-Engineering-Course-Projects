package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
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
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }
        Book book  = Mapper.toBookObject((BookDto) dto);
        String username  =((BookDto) dto).getUsername();

        bookService.addBook(book,username);
        return new Response(true,"Book added successfully.",null);
    }

    @Override
    public Class<?> getDtoClass() {
        return BookDto.class;
    }
}

