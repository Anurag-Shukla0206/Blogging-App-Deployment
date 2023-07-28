package com.anurag.BloggingPlatformAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String commentBody;



    @ManyToOne
    @JoinColumn(name = "fk_comment_post_id")
    public Post blogPost;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    private User commenter;


}
