package eu.dnb.openbankingweb.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.dnb.openbanking.domain.vo.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * Created by rmang on 17-03-2018.
 */
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler{

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ExceptionResponse> handleHttpClientErrorException(HttpClientErrorException ex) throws IOException {
        return new ResponseEntity<ExceptionResponse>(objectMapper.readValue(ex.getResponseBodyAsString(), ExceptionResponse.class), ex.getStatusCode());
    }
}
