package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.Book;
import Infrastucure.Mapper;
import Objects.AuthorDto;
import Objects.BookDto;
import Objects.CartDto;

public class AddCartCommand implements ICommand {
    private final ShopService shopService;

    public AddCartCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof AuthorDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        Book book = Mapper.toBookObject((BookDto) dto);
        return shopService.addBook(book);
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

