package com.example.muzfi.Repository;

import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Model.Post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findAllByAuthorId(String authorId);

    List<Post> findAllByPostCategory(GenreType postCategory);

    List<Post> findAllByCommunity(String community);

    List<Post> findAllByAuthorIdAndIsDraft(String authorId, boolean isDraft);

    Collection<Object> findTopNByOrderByCreatedDateDesc(int limit);

    List<Post> findTopNByIsDraftFalseOrderByCreatedDateDesc(int limit);
}