package Business.commands;

import Business.ICommand;
import Business.WalletService;
import Business.models.Transaction;
import Infrastucure.Mapper;
import Objects.AddCreditDto;
import Objects.CartDto;

public class AddCreditCommand implements ICommand {
    private final WalletService walletService;

    public AddCreditCommand(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof AddCreditDto)) {
            throw new IllegalArgumentException("Expected AuthorDto");
        }

        Transaction transaction= Mapper.ToTransaction((AddCreditDto) dto);
        return walletService.AddCredit(transaction);
    }

    @Override
    public Class<?> getDtoClass() {
        return CartDto.class;
    }
}

