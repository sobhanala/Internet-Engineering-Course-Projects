package Business.commands;

import Business.ICommand;
import Business.services.UserService;
import Objects.PurchesDto;
import Objects.Response;

public class ShowAuthorDetailCommand implements ICommand {
    private final UserService userService;

    public ShowAuthorDetailCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof PurchesDto)) {
            throw new IllegalArgumentException("Expected PersonDto");
        }
        String username= ((PurchesDto) dto).getUsername();
        return new Response(true,"Added user."
                ,userService.getAuthorDetails(username));

    }

    @Override
    public Class<?> getDtoClass() {
        return PurchesDto.class;
    }
}

