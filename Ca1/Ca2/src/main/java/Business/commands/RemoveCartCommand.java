package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.PendPurches;
import Infrastucure.Mapper;
import Objects.CartDto;

public class RemoveCartCommand implements ICommand {
    private final ShopService shopService;

    public RemoveCartCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof CartDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }
        PendPurches cart= Mapper.ToPendPurches((CartDto) dto);

        return shopService.removeCart(cart);
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

