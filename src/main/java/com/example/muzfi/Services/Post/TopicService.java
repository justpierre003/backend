package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Dto.PostDto.TopicCreateDto;
import com.example.muzfi.Dto.PostDto.TopicUpdateDto;
import com.example.muzfi.Model.Post.Topic;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TopicService {
    Optional<PostDetailsDto> createTopic(TopicCreateDto topicDto) throws IOException;

    Optional<Topic> getTopicById(String topicId);

    Optional<List<Topic>> getTopicsByUserId(String userId);

    Optional<List<Topic>> getDraftTopicsByUserId(String userId);

    Optional<PostDetailsDto> updateTopic(TopicUpdateDto updateDto);

    Optional<List<Topic>> getAllTopics();
}
