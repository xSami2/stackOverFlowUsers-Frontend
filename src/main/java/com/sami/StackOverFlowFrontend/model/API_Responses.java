package com.sami.StackOverFlowFrontend.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class API_Responses<T> {

    private boolean success;
    private String message;
    private T data;

}
