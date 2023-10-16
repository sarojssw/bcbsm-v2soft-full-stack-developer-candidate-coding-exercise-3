package com.bcbs.customer.survey.repository;

import com.bcbs.customer.survey.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findFeedbackByUsername(@Param("fk_username") String username);
}
