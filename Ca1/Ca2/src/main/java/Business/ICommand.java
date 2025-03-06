package Business;

import Objects.Response;

public interface ICommand {
    Response execute(Object dto) throws Exception;
     Class<?> getDtoClass();
}
