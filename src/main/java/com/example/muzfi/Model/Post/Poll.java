package com.example.muzfi.Model.Post;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Enums.PostCategory;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "polls")
public class Poll {
    @Id
    private String id;

    private String postId;

    private String authorId;

    private PostAuthorDto author;

    @NotNull
    private String title;

    private String text;

    private List<String> pollOptionIds;

    private LocalDateTime pollDeadline;

    private Boolean isLiked;

    private Integer comments;

    private Integer likes;

    private List<PostCategory> postCategories;

    private List<String> tags;

    @NotNull
    private GenreType postCategory;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;


}
