package lt.codeacademy.advice;

import lt.codeacademy.exception.ProductNotFoundException;
import lt.codeacademy.exception.data.ExceptionResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlingProductNotFound(ProductNotFoundException exception){
        return new ExceptionResponse(String.format("Product %s not found",exception.getUuid()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlingEmptyResultDataAccess(Exception exception){
        return new ExceptionResponse(exception.getMessage(),HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(FileExeption.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ExceptionResponse handlingFileExeption(FileExeption exception){
//        return new ExceptionResponse(exception.getMessage(),HttpStatus.BAD_REQUEST);
//    }


}
