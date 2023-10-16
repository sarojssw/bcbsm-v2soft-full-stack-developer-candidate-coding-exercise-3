package com.bcbs.customer.survey.utils;

import com.bcbs.customer.survey.dto.FeedbackResponse;
import com.bcbs.customer.survey.dto.ViewFeedbackResponse;
import com.bcbs.customer.survey.entity.Feedback;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class SurveyUtils {

    private SurveyUtils(){
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }

    public static ViewFeedbackResponse getFeedbacksList(List<Feedback> feedbackList){
        ViewFeedbackResponse viewFeedbackResponse = new ViewFeedbackResponse();
        List<FeedbackResponse> feedbackResponseList = new ArrayList<FeedbackResponse>();
        for(Feedback feedback : feedbackList){
            FeedbackResponse feedbackResponse = new FeedbackResponse();
            feedbackResponse.setComment(feedback.getComment());
            feedbackResponse.setRating(feedback.getRating());
            feedbackResponse.setUsername(feedback.getUsername());
            feedbackResponse.setCommentDate(feedback.getDate());
            feedbackResponseList.add(feedbackResponse);
        }

        viewFeedbackResponse.setFeedbackResponse(feedbackResponseList);
        return viewFeedbackResponse;
    }
}
