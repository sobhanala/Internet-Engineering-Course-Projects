package Business.commands;

import Business.ICommand;
import Business.ShopService;
import Business.models.Person;
import Infrastucure.Mapper;
import Objects.PersonDto;

// Concrete command implementations
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
        Person person = Mapper.toPersonObject((PersonDto) dto); // Mapping here
        return shopService.addUser(person); // Pass business entity to service
    }

    @Override
    public Class<?> getDtoClass() {
        return PersonDto.class;
    }
}

