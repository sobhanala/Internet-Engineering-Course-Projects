package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.ShopService;
import Business.models.PendPurches;
import Infrastucure.Mapper;
import Objects.CartDto;
import Objects.Response;

public class RemoveCartCommand implements ICommand {
    private final ShopService shopService;

    public RemoveCartCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof CartDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }
        PendPurches cart= Mapper.ToPendPurches((CartDto) dto);

         shopService.removeCart(cart);
        return new Response(true,"Removed book from cart.",null);

        }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

