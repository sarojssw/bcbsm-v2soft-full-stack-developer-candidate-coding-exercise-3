package com.bcbs.customer.survey.dto;

import lombok.Data;

import java.util.Date;

@Data
public class FeedbackResponse {
    private Integer rating;
    private String comment;
    private String username;
    private Date commentDate;
}
