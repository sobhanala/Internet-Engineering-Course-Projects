package Business.exeptions;

import lombok.Getter;

@Getter
public class ShopException extends RuntimeException {
    private final ErrorType errorType;

    public ShopException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ShopException(ErrorType errorType, String additionalInfo) {
        super(String.format("%s: %s", errorType.getMessage(), additionalInfo));
        this.errorType = errorType;
    }

    @Getter
    public enum ErrorType {
        USER_NOT_FOUND("User not found"),
        USER_NOT_CUSTOMER("User is not a customer"),
        USER_NOT_ADMIN("User is not an admin"),
        ADMIN_ACCESS_DENIED("Admins cannot perform this action"),
        BOOK_NOT_FOUND("Book not found"),
        BOOK_ALREADY_EXISTS("Book already exists"),
        CART_EMPTY("Cart is empty"),
        INSUFFICIENT_FUNDS("Insufficient funds"),
        USER_ALREADY_EXISTS("User already exists"),
        AUTHOR_ALREADY_EXISTS("Author already exists"),

        INVALID_DATE("Invalid date"),
        INVALID_YEAR_RANGE("Invalid year range: 'from' must be less than or equal to 'to'"),
        INVALID_YEAR_FORMAT("Invalid year format"),
        CART_FULL("Cart cannot hold more than 10 items"),
        INVALID_DATA("Invalid data provided"),
        INVALID_ADDRESS("Invalid Address provided"),
        INVALID_EMAIL("Invalid EMAIL provided"),
        INVALID_PASSWORD("Invalid Password provided"),
        INVALID_USERNAME("Invalid Username provided"),


        GENERIC_ERROR("An unexpected error occurred");

        private final String message;

        ErrorType(String message) {
            this.message = message;
        }

    }
}