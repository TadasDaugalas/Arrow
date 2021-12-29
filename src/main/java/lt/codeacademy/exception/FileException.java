package lt.codeacademy.exception;

import lombok.Getter;

@Getter
public class FileException extends RuntimeException {
    private final String message;

    public FileException(String message) {
        this.message = message;
    }
}
