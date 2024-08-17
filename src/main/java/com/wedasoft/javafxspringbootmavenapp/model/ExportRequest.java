package com.wedasoft.javafxspringbootmavenapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class ExportRequest {

    public ExportRequest(List<User> users, String orderType) {
        this.users = users;
        this.orderType = orderType;
    }

    List<User> users;
    String orderType;


}
