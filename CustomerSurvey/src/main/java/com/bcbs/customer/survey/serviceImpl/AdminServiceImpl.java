package com.bcbs.customer.survey.serviceImpl;

import com.bcbs.customer.survey.dto.ViewFeedbackResponse;
import com.bcbs.customer.survey.entity.Feedback;
import com.bcbs.customer.survey.repository.FeedbackRepository;
import com.bcbs.customer.survey.service.AdminService;
import com.bcbs.customer.survey.utils.SurveyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    FeedbackRepository feedbackRepository;

    public ViewFeedbackResponse getFeedbacks(){
        List<Feedback> feedbackList = feedbackRepository.findAll();
        return SurveyUtils.getFeedbacksList(feedbackList);
    }
}
