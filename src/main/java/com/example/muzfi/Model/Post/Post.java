package com.example.muzfi.Model.Post;

import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Enums.PostType;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Post {

    @Id
    private String id;

    private String authorId;

    private PostType postType;

    private String postTypeId;

    private String community;

    private Integer shares;

    private Boolean isEnablePostReplyNotification;

    private Boolean isDraft;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    @NotNull
    private GenreType postCategory;

    @CreatedDate
    private LocalDateTime createdDate;
}

