package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.UserService;
import Business.models.Author;
import Infrastucure.Mapper;
import Objects.AuthorDto;
import Objects.Response;

public class AddAuthorCommand implements ICommand {
    private final UserService userService;

    public AddAuthorCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof AuthorDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }
        Author author = Mapper.toAuthorObject((AuthorDto) dto);
        String username = ((AuthorDto) dto).getUsername();
        userService.addAuthor(author,username);
        return new Response(true,"Author added successfully.",null);
    }

    @Override
    public Class<?> getDtoClass() {
        return AuthorDto.class;
    }
}

