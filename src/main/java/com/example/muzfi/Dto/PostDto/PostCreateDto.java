package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.GenreType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostCreateDto {

    private String authorId;

    private GenreType postCategory;

    private String community;

    private String title;

    private String subTitle;

    private String description;

    private Boolean isEnablePostReplyNotification;

    private LocalDateTime createdDateTime;

    private Boolean isDraft;
    private boolean isReviewed;
}
