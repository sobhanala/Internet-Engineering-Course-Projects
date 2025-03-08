package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.ShopService;
import Objects.PurchesDto;
import Objects.Response;

public class PurchaseCartCommand implements ICommand {
    private final ShopService shopService;

    public PurchaseCartCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof PurchesDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        String Username= ((PurchesDto) dto).getUsername();
        return new Response(true,"Purchase completed successfully", shopService.purchase(Username));
    }

    @Override
    public Class<?> getDtoClass() {
        return PurchesDto.class;
    }
}

