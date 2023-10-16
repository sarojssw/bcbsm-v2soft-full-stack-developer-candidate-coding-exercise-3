package com.bcbs.customer.survey.controller;

import com.bcbs.customer.survey.constants.SurveyConstants;
import com.bcbs.customer.survey.dto.SubmitFeedbackRequest;
import com.bcbs.customer.survey.dto.FeedbackResponse;
import com.bcbs.customer.survey.dto.ViewFeedbackRequest;
import com.bcbs.customer.survey.dto.ViewFeedbackResponse;
import com.bcbs.customer.survey.service.UserService;
import com.bcbs.customer.survey.utils.SurveyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/feedback/view")
    public ResponseEntity<ViewFeedbackResponse> viewFeedback(@RequestBody ViewFeedbackRequest viewFeedbackRequest){
        //return ResponseEntity.ok("Hello User");
        return ResponseEntity.ok(userService.getFeedbacks(viewFeedbackRequest));
    }

    @PostMapping("/feedback/submit")
    public ResponseEntity<String> submitFeedback(@RequestBody SubmitFeedbackRequest submitFeedbackRequest){
        try{
            return userService.submitFeedback(submitFeedbackRequest);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return SurveyUtils.getResponseEntity(SurveyConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
