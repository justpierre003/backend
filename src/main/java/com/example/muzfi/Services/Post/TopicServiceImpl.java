package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Dto.PostDto.TopicCreateDto;
import com.example.muzfi.Dto.PostDto.TopicUpdateDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Enums.TopicType;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.Post.Topic;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Repository.TopicRepository;
import com.example.muzfi.Services.User.UserService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    private final PostRepository postRepository;

    private final UserService userService;

    private final PostService postService;

    private final PostManager postManager;

    @Autowired
    public TopicServiceImpl(TopicRepository topicRepository, PostService postService, PostRepository postRepository, UserService userService, PostManager postManager) {
        this.topicRepository = topicRepository;
        this.postRepository = postRepository;
        this.userService = userService;
        this.postManager = postManager;
        this.postService = postService;
    }

    @Override
    public Optional<PostDetailsDto> createTopic(TopicCreateDto topicDto) throws IOException {
        Optional<User> user = userService.getUserById(topicDto.getAuthorId());
        //create post
        Post newPost = new Post();
        newPost.setAuthorId(topicDto.getAuthorId());
        if(topicDto.getTopicType() == TopicType.CONTENT){
            newPost.setPostType(PostType.PROD_TOPIC_IMG);
        }else{ newPost.setPostType(PostType.PROD_TOPIC);}
        newPost.setIsEnablePostReplyNotification(topicDto.getIsEnablePostReplyNotification());
        newPost.setCreatedDateTime(topicDto.getCreatedDateTime());
        newPost.setUpdatedDateTime(topicDto.getCreatedDateTime());
        newPost.setIsDraft(topicDto.getIsDraft());
        newPost.setCommunity(topicDto.getCommunity());
        newPost.setPostCategory(topicDto.getPostCategory());

        //create topic
        Topic newTopic = new Topic();
        newTopic.setAuthorId(topicDto.getAuthorId());
        newTopic.setTitle(topicDto.getTitle());
        newTopic.setTopicType(topicDto.getTopicType());
        newTopic.setText(topicDto.getDescription());
        newTopic.setPostCategory(topicDto.getPostCategory());

        if(topicDto.getTopicType() == TopicType.CONTENT){
            newTopic.setImage(topicDto.getImage());

        }
        newTopic.setCreatedDateTime(topicDto.getCreatedDateTime());
        newTopic.setUpdatedDateTime(topicDto.getCreatedDateTime());

        User existingUser = user.get();
        existingUser.setMuzPoints(existingUser.getMuzPoints() + 15);
        existingUser.setNoOfPosts(existingUser.getNoOfPosts() + 1);
        userService.updateUser(existingUser);


        //save post and topic
        Post post = postRepository.save(newPost);
        Topic topic = topicRepository.save(newTopic);

        //update post and topic with ids
        post.setPostTypeId(topic.getId());
        topic.setPostId(post.getId());

        Post postUpdated = postRepository.save(post);
        Topic topicUpdated = topicRepository.save(topic);

        //return created post
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, topicUpdated, authorOptional.get());

        return Optional.ofNullable(postDetailsDto);
    }

    @Override
    public Optional<Topic> getTopicById(String topicId) {
        Optional<Topic> topicOptional = topicRepository.findById(topicId);

        if (topicOptional.isPresent()) {
            Topic topic = topicOptional.get();
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(topic.getAuthorId());
            PostAuthorDto author = authorOptional.get();
            Optional<PostDetailsDto> postOptional = postService.getPostById(topic.getPostId());
            topic.setIsLiked(postOptional.get().getIsLiked());
            topic.setLikes(postOptional.get().getLikes());
            topic.setComments(postOptional.get().getComments());
            topic.setAuthor(author);
            return Optional.of(topic);
        } else {
            return Optional.empty();
        }
    }

    //Get user topics - Filtered out draft topics
    @Override
    public Optional<List<Topic>> getTopicsByUserId(String userId) {
        List<Topic> topics = topicRepository.findAllByAuthorId(userId);

        if (topics.isEmpty()) {
            return Optional.empty();
        }

        List<Topic> topicList = new ArrayList<>();

        for (Topic topic : topics) {
            Optional<Post> postOpt = postRepository.findById(topic.getPostId());

            if (postOpt.isPresent()) {
                Post post = postOpt.get();

                if (post.getIsDraft() == null || post.getIsDraft().equals(false)) {
                    topicList.add(topic);
                }

                topicList.add(topic);
            }
        }

        return Optional.of(topicList);
    }


    //Get user draft topics
    @Override
    public Optional<List<Topic>> getDraftTopicsByUserId(String userId) {
        List<Topic> topics = topicRepository.findAllByAuthorId(userId);

        if (topics.isEmpty()) {
            return Optional.empty();
        }

        List<Topic> topicList = new ArrayList<>();

        for (Topic topic : topics) {
            Optional<Post> postOpt = postRepository.findById(topic.getPostId());

            if (postOpt.isPresent()) {
                Post post = postOpt.get();

                if (post.getIsDraft() != null && post.getIsDraft().equals(true)) {
                    topicList.add(topic);
                }
            }
        }

        return Optional.of(topicList);
    }


    @Override
    public Optional<PostDetailsDto> updateTopic(TopicUpdateDto updateDto) {
        Optional<Topic> topicOpt = topicRepository.findById(updateDto.getId());
        Optional<Post> postOpt = postRepository.findById(updateDto.getPostId());

        if (topicOpt.isEmpty() || postOpt.isEmpty()) {
            return Optional.empty();
        }

        Topic topic = topicOpt.get();
        Post post = postOpt.get();

        topic.setTitle(updateDto.getTitle());
        topic.setTopicType(updateDto.getTopicType());
        topic.setText(updateDto.getText());
        topic.setPostCategory(updateDto.getPostCategory());
        topic.setTags(updateDto.getTags());
        topic.setUpdatedDateTime(LocalDateTime.now());

        post.setUpdatedDateTime(LocalDateTime.now());

        Post postUpdated = postRepository.save(post);
        Topic topicUpdated = topicRepository.save(topic);

        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, topicUpdated, authorOptional.get());

        return Optional.of(postDetailsDto);
    }

    //Get topics - Filtered out draft topics
    @Override
    public Optional<List<Topic>> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();

        if (topics.isEmpty()) {
            return Optional.empty();
        }

        List<Topic> topicList = new ArrayList<>();

        for (Topic topic : topics) {
            Optional<Post> postOpt = postRepository.findById(topic.getPostId());

            if (postOpt.isPresent()) {
                Post post = postOpt.get();

                if (post.getIsDraft() == null || post.getIsDraft().equals(false)) {
                    topicList.add(topic);
                }
            }
        }

        return Optional.of(topicList);
    }
}
