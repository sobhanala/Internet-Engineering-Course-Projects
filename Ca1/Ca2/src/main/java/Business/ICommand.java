package Business;

public interface ICommand {
    String execute(Object dto) throws Exception;
     Class<?> getDtoClass();
}
