package Business.commands;

import Business.ICommand;
import Business.services.UserService;
import Business.models.Person;
import Infrastucure.Mapper;
import Objects.PersonDto;
import Objects.Response;

public class AddUserCommand implements ICommand {
    private final UserService userService;

    public AddUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof PersonDto)) {
            throw new IllegalArgumentException("Expected PersonDto");
        }
            Person person = Mapper.toPersonObject((PersonDto) dto);
            userService.addUser(person);
            return new Response(true,"Added user.","");

    }

    @Override
    public Class<?> getDtoClass() {
        return PersonDto.class;
    }
}

