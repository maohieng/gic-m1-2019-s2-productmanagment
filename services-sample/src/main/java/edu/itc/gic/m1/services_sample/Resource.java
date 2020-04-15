package edu.itc.gic.m1.services_sample;

/**
 * This class is used for ...
 *
 * @autor MAO Hieng 4/10/2020
 */
public class Resource<T> {

    public static class Factory {
        public static <T> Resource<T> loading() {
            return new Resource<>(Status.LOADING);
        }

        public static <T> Resource<T> error(String message, Throwable cause) {
            Resource<T> resource = new Resource<>(Status.ERROR);
            resource.message = message;
            resource.cause = cause;
            return resource;
        }

        public static <T> Resource<T> success(T data) {
            Resource<T> resource = new Resource<>(Status.SUCCESS);
            resource.data = data;
            return resource;
        }
    }

    enum Status {
        LOADING, SUCCESS, ERROR
    }

    final Status status;
    T data;

    String message;
    Throwable cause;

    protected Resource(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }
}
