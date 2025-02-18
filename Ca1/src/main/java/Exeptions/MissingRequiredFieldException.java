package Exeptions;

import lombok.Getter;

@Getter
public class MissingRequiredFieldException extends BookingException {
    private final String fieldName;

    public MissingRequiredFieldException(String message, String fieldName) {
        super(message, "MISSING_FIELD");
        this.fieldName = fieldName;
    }

}