package me.star.handler;

import lombok.Data;

/**
 * @author Zheng Jie
 * @date 2018-11-23
 */
@Data
public class ApiError {

    private Integer status = 400;
    private Long timestamp;
    private String message;

    private ApiError() {
        timestamp = System.currentTimeMillis();
    }

    public static ApiError error(String message){
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(Integer status, String message){
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        return apiError;
    }
}


