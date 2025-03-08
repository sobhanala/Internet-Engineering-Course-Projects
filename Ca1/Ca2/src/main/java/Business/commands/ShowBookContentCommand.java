package Business.commands;

import Business.ICommand;
import Business.services.UserService;
import Objects.CartDto;
import Objects.Response;

public class ShowBookContentCommand implements ICommand {
    private final UserService userService;

    public ShowBookContentCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof CartDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        String bookTitle  = ((CartDto) dto).getTitle();
        String username  = ((CartDto) dto).getUsername();

        return new Response(true,"Removed book from cart."
                ,userService.getContent(bookTitle,username));
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

