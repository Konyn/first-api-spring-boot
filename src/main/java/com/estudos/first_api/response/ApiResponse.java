package com.estudos.first_api.response;

public class ApiResponse<T> {
    private int status;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters e Setters
    public int getStatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
