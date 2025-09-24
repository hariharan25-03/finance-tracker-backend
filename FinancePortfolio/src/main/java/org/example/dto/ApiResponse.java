package org.example.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data

public class ApiResponse<T> {

    private String message;
    private int statusCode;
    private T data;

    public ApiResponse() {}

    public ApiResponse(String message, HttpStatus status, T data) {
        this.message = message;
        this.statusCode = status.value();
        this.data = data;
    }
    public ApiResponse(String message, HttpStatus status) {
        this.message = message;
        this.statusCode = status.value();
        this.data = data;
    }
    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

