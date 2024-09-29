package com.todo.todo.model.payload.response;

import com.todo.todo.model.enumaration.ResponseCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericRestResponse <T> {

    private int code;
    private String message;
    private T data;

    public static <T> GenericRestResponse<T> generateResponse(T data, ResponseCodes responseCode) {
        GenericRestResponse<T> genericRestResponse = new GenericRestResponse<>();
        genericRestResponse.setData(data);
        genericRestResponse.setMessage(responseCode.getMessage());
        genericRestResponse.setCode(responseCode.getCode());
        return genericRestResponse;
    }

    public static <T> GenericRestResponse<T> generateErrorResponse(String message) {
        GenericRestResponse<T> genericRestResponse = new GenericRestResponse<>();
        genericRestResponse.setCode(-1);
        genericRestResponse.setMessage(message);
        return genericRestResponse;
    }
}
