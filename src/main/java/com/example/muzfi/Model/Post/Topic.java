package com.example.muzfi.Model.Post;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Enums.PostCategory;
import com.example.muzfi.Enums.TopicType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "topics")
public class Topic {
    @Id
    private String id;

    private String postId;

    private String authorId;

    private PostAuthorDto author;

    @NotNull
    private String title;

    private TopicType topicType;

    private String text;

    private String Content;

    private Boolean isLiked;

    private Integer comments;

    private Integer likes;

    private Binary image;

    private String links;
    @NotNull
    private GenreType postCategory;

    private String Community;

    private String tags;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;


}
