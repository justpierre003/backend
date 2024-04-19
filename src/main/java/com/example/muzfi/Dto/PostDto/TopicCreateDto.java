package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Enums.PostCategory;
import com.example.muzfi.Enums.TopicType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicCreateDto extends PostCreateDto{

    private TopicType topicType;

    private Binary image;

}
