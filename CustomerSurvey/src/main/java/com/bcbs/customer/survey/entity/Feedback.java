package com.bcbs.customer.survey.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "feedback")
public class Feedback {

    //private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    private Date date;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "fk_username", referencedColumnName = "username")
    @Column(name = "fk_username")
    private String username;


}
