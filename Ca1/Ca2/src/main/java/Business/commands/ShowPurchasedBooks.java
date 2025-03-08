package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.ShopService;
import Objects.Response;
import Objects.UsernameDto;

public class ShowPurchasedBooks implements ICommand {
    private final ShopService shopService;

    public ShowPurchasedBooks(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof UsernameDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        var username= ((UsernameDto) dto).getUsername();
        return new Response(true,"Added to the cart."
                , shopService.showPurchasedBooks(username));
    }

    @Override
    public Class<?> getDtoClass() {
        return UsernameDto.class;
    }
}
