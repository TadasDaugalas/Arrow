package lt.codeacademy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    private UUID uuid;
}