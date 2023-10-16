package com.bcbs.customer.survey.dto;

import lombok.Data;

import java.util.List;

@Data
public class ViewFeedbackResponse {
    private List<FeedbackResponse> feedbackResponse;
}
