package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.Author;
import Infrastucure.Mapper;
import Objects.AuthorDto;
public class AddAuthorCommand implements ICommand {
    private final ShopService shopService;

    public AddAuthorCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof AuthorDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        Author author = Mapper.toAuthorObject((AuthorDto) dto);
        return shopService.addAuthor(author);
    }

    @Override
    public Class<?> getDtoClass() {
        return AuthorDto.class;
    }
}

