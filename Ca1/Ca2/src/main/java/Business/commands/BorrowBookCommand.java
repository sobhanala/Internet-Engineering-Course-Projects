package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.ShopService;
import Infrastucure.Mapper;
import Objects.BorrowBookDto;
import Objects.Response;

public class BorrowBookCommand implements ICommand {
    private final ShopService shopService;

    public BorrowBookCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof BorrowBookDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }

        var borrowdata= Mapper.ToBorrowData((BorrowBookDto) dto);
        shopService.borrowBook(borrowdata);
        return new Response(true,"Added borrowed book to cart.",null);
    }

    @Override
    public Class<?> getDtoClass() {
        return BorrowBookDto.class;
    }

}
