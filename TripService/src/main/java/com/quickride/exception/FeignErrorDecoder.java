package com.quickride.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quickride.dto.ExceptionResponse;
import com.quickride.external.exception.DriverNotFoundException;
import com.quickride.external.exception.ServiceUnavailableException;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component @Slf4j
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        String message = "";
        try {
            if(response.body()!=null){
                String body = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
                ObjectMapper objectMapper = new ObjectMapper();
                ExceptionResponse exceptionResponse = objectMapper.readValue(body, ExceptionResponse.class);
                message = exceptionResponse.getMessage();
            }

        } catch (IOException e) {
            log.error("Unable to read the body from the response: ", e);
        }

        return switch(response.status()){
            case 404 -> new DriverNotFoundException(message);
            case 503 -> new ServiceUnavailableException(message);
            default -> new FeignException.FeignClientException(response.status(), message, response.request(), null, null);
        };

    }
}
