package com.example.muzfi.Repository;

import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Model.Community;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends MongoRepository<Community, String> {
    Community findByName(String name);
    List<Community> findByTypeAndGenre(String type, GenreType genre);

    List<Community> findByGenre(GenreType genre);

    List<Community> findByCreatorId(String creatorId);

    List<Community> findByNameContainingIgnoreCase(String name);

    List<Community> findByTitleContainingIgnoreCase(String name);

    List<Community> findByDescriptionContainingIgnoreCase(String query);
}
