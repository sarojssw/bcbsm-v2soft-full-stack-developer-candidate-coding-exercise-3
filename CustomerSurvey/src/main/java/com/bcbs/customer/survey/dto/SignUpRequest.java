package com.bcbs.customer.survey.dto;

import lombok.Data;

@Data
public class SignUpRequest {

    private String name;
    private String username;
    private String password;
}
