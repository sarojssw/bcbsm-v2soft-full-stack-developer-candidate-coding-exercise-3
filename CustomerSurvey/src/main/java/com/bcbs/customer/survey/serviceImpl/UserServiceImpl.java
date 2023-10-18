package com.bcbs.customer.survey.serviceImpl;

import com.bcbs.customer.survey.constants.SurveyConstants;
import com.bcbs.customer.survey.dto.SubmitFeedbackRequest;
import com.bcbs.customer.survey.dto.FeedbackResponse;
import com.bcbs.customer.survey.dto.ViewFeedbackRequest;
import com.bcbs.customer.survey.dto.ViewFeedbackResponse;
import com.bcbs.customer.survey.entity.Feedback;
import com.bcbs.customer.survey.repository.FeedbackRepository;
import com.bcbs.customer.survey.repository.UserRepository;
import com.bcbs.customer.survey.service.UserService;
import com.bcbs.customer.survey.utils.SurveyUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    FeedbackRepository feedbackRepository;

    private EntityManager entityManager;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUserName(username);
            }
        };
    }

    public ResponseEntity<String> submitFeedback(SubmitFeedbackRequest submitFeedbackRequest){
        try{
            if(validateFeedback(submitFeedbackRequest)){
                feedbackRepository.save(getFeedBackFromMap(submitFeedbackRequest));
                return SurveyUtils.getResponseEntity("Feedback submitted successfully", HttpStatus.OK);
            } else{
                return SurveyUtils.getResponseEntity("Pass the proper feedback", HttpStatus.BAD_REQUEST);
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return SurveyUtils.getResponseEntity(SurveyConstants.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateFeedback(SubmitFeedbackRequest submitFeedbackRequest){
        if(submitFeedbackRequest ==null || submitFeedbackRequest.getRating()<1){
            return false;
        }
        return true;
    }

    private Feedback getFeedBackFromMap(SubmitFeedbackRequest submitFeedbackRequest){
        Feedback feedback = new Feedback();
        feedback.setComment(submitFeedbackRequest.getComment());
        feedback.setRating(submitFeedbackRequest.getRating());
        feedback.setUsername(submitFeedbackRequest.getUsername());
        feedback.setDate(new Date(System.currentTimeMillis()));

        return feedback;
    }

    public ViewFeedbackResponse getFeedbacks(ViewFeedbackRequest viewFeedbackRequest){
        String username = viewFeedbackRequest.getUsername();
        List<Feedback> feedbackList = new ArrayList<Feedback>();
        if("Admin".equals(username)){
            feedbackList = feedbackRepository.findAll();
        } else {
            feedbackList = feedbackRepository.findFeedbackByUsername(username);
        }
        return SurveyUtils.getFeedbacksList(feedbackList);
    }


}
