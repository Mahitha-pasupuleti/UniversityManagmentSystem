package com.userprofile.CustomExceptions;

public class ProfileAlreadyCreatedException extends RuntimeException {
    public ProfileAlreadyCreatedException(String message) {
        super(message);
    }
}
