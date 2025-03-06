package Business.commands;

import Business.ICommand;
import Business.UserService;
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
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        Author author = Mapper.toAuthorObject((AuthorDto) dto);
        userService.addAuthor(author);
        return new Response(true,"Removed book from cart.","");
    }

    @Override
    public Class<?> getDtoClass() {
        return AuthorDto.class;
    }
}

