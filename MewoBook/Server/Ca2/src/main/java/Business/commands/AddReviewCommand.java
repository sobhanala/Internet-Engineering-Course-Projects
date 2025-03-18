package Business.commands;

import Business.ICommand;
import Business.exeptions.ShopException;
import Business.services.BookService;
import Objects.AddReviewDto;
import Objects.Response;

public class AddReviewCommand implements ICommand {
    private final BookService bookService;

    public AddReviewCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Response execute(Object dto) throws Exception {
        if (!(dto instanceof AddReviewDto)) {
            throw new ShopException(ShopException.ErrorType.GENERIC_ERROR);
        }
        var bookTitle  = ((AddReviewDto) dto).getTitle();
        var username  = ((AddReviewDto) dto).getUsername();
        var rate  = ((AddReviewDto) dto).getRate();
        var comment  = ((AddReviewDto) dto).getComment();
        bookService.addReview(username,bookTitle,rate,comment);

        return new Response(true,"Review added successfully.",null);
    }

    @Override
    public Class<?> getDtoClass() {
        return AddReviewDto.class;
    }
}

