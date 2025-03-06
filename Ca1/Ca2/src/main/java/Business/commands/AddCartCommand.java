package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.PendPurches;
import Infrastucure.Mapper;
import Objects.CartDto;

public class AddCartCommand implements ICommand {
    private final ShopService shopService;

    public AddCartCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof CartDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }

        PendPurches cart= Mapper.ToPendPurches((CartDto) dto);
        return shopService.addCart(cart);
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

