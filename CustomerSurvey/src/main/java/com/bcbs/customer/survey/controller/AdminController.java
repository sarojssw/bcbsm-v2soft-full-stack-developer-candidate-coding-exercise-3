package com.bcbs.customer.survey.controller;

import com.bcbs.customer.survey.dto.ViewFeedbackRequest;
import com.bcbs.customer.survey.dto.ViewFeedbackResponse;
import com.bcbs.customer.survey.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/feedback/view")
    public ResponseEntity<ViewFeedbackResponse> viewFeedback(){
        //return ResponseEntity.ok("Hello User");
        return ResponseEntity.ok(adminService.getFeedbacks());
    }


}
