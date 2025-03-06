package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.Transaction;
import Infrastucure.Mapper;
import Objects.AddCreditDto;
import Objects.CartDto;
import Objects.Response;

public class AddCreditCommand implements ICommand {
    private final ShopService shopService;

    public AddCreditCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof AddCreditDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }

        Transaction transaction= Mapper.ToTransaction((AddCreditDto) dto);
        shopService.addCredit(transaction);
        return new Response(true,"Credit added successfully","");
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

