package com.github.diogocerqueiralima.exceptions;

public class DockerException extends RuntimeException {

    public DockerException(String message) {
        super(message);
    }

    public DockerException(String message, Throwable cause) {
        super(message, cause);
    }

}
