package com.bcbs.customer.survey.dto;

import lombok.Data;

@Data
public class SubmitFeedbackRequest {

    private Integer rating;
    private String comment;
    private String username;

}
