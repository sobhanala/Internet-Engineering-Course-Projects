package Business.commands;

import Business.ICommand;
import Business.models.Transaction;
import Business.services.UserService;
import Infrastucure.Mapper;
import Objects.AddCreditDto;
import Objects.Response;

public class AddCreditCommand implements ICommand {
    private final UserService userService;

    public AddCreditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof AddCreditDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }

        Transaction transaction= Mapper.ToTransaction((AddCreditDto) dto);
        userService.addCredit(transaction);
        return new Response(true,"Credit added successfully","");
    }

    @Override
    public Class<?> getDtoClass() {
        return AddCreditDto.class;
    }
}

