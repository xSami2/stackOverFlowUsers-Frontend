package com.wedasoft.javafxspringbootmavenapp.model;

import lombok.Data;


@Data
public class User {

    private long user_id;
    private long account_id;
    private long last_access_date;
    private String display_name;
    private long userAge;
    private long reputation;
    private String location;
    private String userType;
    private long VIEW_COUNT;
    private long QUESTION_COUNT;
    private long ANSWER_COUNT;
    private String PROFILE_IMAGE;






}
