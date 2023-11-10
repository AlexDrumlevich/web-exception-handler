package telran.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionsHandler {

	@ExceptionHandler({IllegalStateException.class})
	ResponseEntity<String> illegalStateExceptionHandler(Exception e) {
		log.error(e.toString());
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NotFoundException.class})
	ResponseEntity<String> notFoundExceptionHandler(Exception e) {
		log.error(e.toString());
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST); 
	}
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
    	StringBuilder messageBuilder = new StringBuilder();
    	e.getBindingResult().getFieldErrors().stream().forEach(err -> {
    		log.error(err.toString());
    		messageBuilder.append(err.getDefaultMessage()).append("\n");
    	});
    	
        return new ResponseEntity<String>(messageBuilder.toString(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
    	log.error(e.toString());
    	return new ResponseEntity<String>(e. getMessage(), HttpStatus.BAD_REQUEST); 
    }
	
}
