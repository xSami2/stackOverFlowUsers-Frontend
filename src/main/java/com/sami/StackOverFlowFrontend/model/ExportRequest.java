package com.sami.StackOverFlowFrontend.model;

import lombok.Data;

import java.util.List;

@Data
public class ExportRequest {



    List<User> users;
    String orderType;

    public ExportRequest(List<User> users, String orderType) {
        this.users = users;
        this.orderType = orderType;
    }

}
