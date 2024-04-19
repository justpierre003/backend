package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Enums.PostCategory;
import com.example.muzfi.Enums.TopicType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicUpdateDto {
    private String id;

    private String postId;

    private String title;

    private TopicType topicType;

    private String text;

    private Binary image;

    private GenreType postCategory;

    private String tags;
}
