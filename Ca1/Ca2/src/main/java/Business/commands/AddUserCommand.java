package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.Person;
import Infrastucure.Mapper;
import Objects.PersonDto;

public class AddUserCommand implements ICommand {
    private final ShopService shopService;

    public AddUserCommand(ShopService shopService) {
        this.shopService = shopService;
    }

    @Override
    public String execute(Object dto) throws Exception {
        if (!(dto instanceof PersonDto)) {
            throw new IllegalArgumentException("Expected PersonDto");
        }
            Person person = Mapper.toPersonObject((PersonDto) dto);
            return shopService.addUser(person);

    }

    @Override
    public Class<?> getDtoClass() {
        return PersonDto.class;
    }
}

