package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.Book;
import Infrastucure.Mapper;
import Objects.BookDto;
public class AddBookCommand implements ICommand {
    private final ShopService shopService;

    public AddBookCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof BookDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        Book book  = Mapper.toBookObject((BookDto) dto);
        return shopService.addBook(book);
    }

    @Override
    public Class<?> getDtoClass() {
        return BookDto.class;
    }
}

