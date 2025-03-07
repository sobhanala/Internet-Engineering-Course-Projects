package Business.commands;

import Business.ICommand;
import Business.services.ShopService;
import Infrastucure.Mapper;
import Objects.BorrowBookDto;
import Objects.CartDto;
import Objects.Response;

public class BorrowBookCommand implements ICommand {
    private final ShopService shopService;

    public BorrowBookCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof BorrowBookDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }

        var borrowdata= Mapper.ToBorrowData((BorrowBookDto) dto);
        shopService.borrowBook(borrowdata);
        return new Response(true,"Purchase completed successfully",null);
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }

}
