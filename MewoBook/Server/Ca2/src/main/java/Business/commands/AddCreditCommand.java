package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
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
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        Transaction transaction= Mapper.ToTransaction((AddCreditDto) dto);
        userService.addCredit(transaction);
        return new Response(true,"Credit added successfully",null);
    }

    @Override
    public Class<?> getDtoClass() {
        return AddCreditDto.class;
    }
}

