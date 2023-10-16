package com.bcbs.customer.survey.repository;

import com.bcbs.customer.survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUserName(@Param("username") String username);

    User findByRole(@Param("role") String role);
}
