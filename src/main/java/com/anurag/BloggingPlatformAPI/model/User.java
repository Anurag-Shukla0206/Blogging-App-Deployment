package com.anurag.BloggingPlatformAPI.model;

import com.anurag.BloggingPlatformAPI.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true)
    @NotBlank
    private String userName;


    @Column(nullable = false,unique = true)
    private String userEmail;


    @Column(nullable = false)
    private String userPassword;



    @Column(nullable = false,unique = true)
    private String userContact;

    @Enumerated(EnumType.STRING)
    private Gender gender;



}
