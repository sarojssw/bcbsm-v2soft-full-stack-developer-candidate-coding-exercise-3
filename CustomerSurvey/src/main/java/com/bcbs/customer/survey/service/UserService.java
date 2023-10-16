package com.bcbs.customer.survey.service;

import com.bcbs.customer.survey.dto.SubmitFeedbackRequest;
import com.bcbs.customer.survey.dto.ViewFeedbackRequest;
import com.bcbs.customer.survey.dto.ViewFeedbackResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

   /* ResponseEntity<String> signUp(Map<String, String> requestMap);*/

    public UserDetailsService userDetailsService();

    public ResponseEntity<String> submitFeedback(SubmitFeedbackRequest submitFeedbackRequest);

    public ViewFeedbackResponse getFeedbacks(ViewFeedbackRequest viewFeedbackRequest);
}
