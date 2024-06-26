package com.example.muzfi.Dto.PostDto;

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
public class CommentDetailsDto {
    private String id;

    private String postId;

    private PostAuthorDto author;

    private String userId;

    private String text;

    private List<CommentReplyDetailsDto> replyComments;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
