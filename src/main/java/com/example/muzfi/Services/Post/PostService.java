package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.PostCreateDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Enums.GenreType;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Optional<PostDetailsDto> createPost(PostCreateDto postDto);

    Optional<List<PostDetailsDto>> getAllPosts();

    Optional<List<PostDetailsDto>> getAllPostsByGenre(GenreType genreType);

    Optional<List<PostDetailsDto>> getAllPostsByCommunity(String community);

    Optional<PostDetailsDto> getPostById(String id);

    Optional<List<PostDetailsDto>> getPostsByUserId(String userId);

    Optional<List<PostDetailsDto>> getDraftPostsByUserId(String userId);

    Optional<PostDetailsDto> publishDraftPost(String postId);

    Optional<String> deletePost(String postId);

    List<Object> getLatestPosts(int limit);

}
