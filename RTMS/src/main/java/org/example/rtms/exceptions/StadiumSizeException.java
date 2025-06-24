package org.example.rtms.exceptions;

import org.example.rtms.model.entity.Stadium;
import org.springframework.http.HttpStatus;

public class StadiumSizeException extends ApiException {
    public StadiumSizeException(int spectators, Stadium stadium) {
        super("Stadium: " + stadium.getName() +  " does not have enough capacity for "+ spectators + " spectators.", HttpStatus.CONFLICT);
    }
}
