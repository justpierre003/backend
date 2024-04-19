package com.example.muzfi.Model;

import com.example.muzfi.Enums.GenreType;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "communities")
public class Community {

    @Id
    private String id;
    @NotEmpty(message = "Name is required")
    private String name;
    private String title;
    private String creatorId;
    private LocalDateTime createdDateTime = LocalDateTime.now();
    private List<String> subscriberIds;
    private List<String> postIds;
    private String about;
    private String sub;
    private String type;
    private GenreType genre;
    private boolean joinable;
    private boolean creatable;
    private List<String> reviews;
    private List<String> similarCommunityIds;
    private List<CommunityRule> rules;
    private int subscriberCount;
    private int rankedSize;
    private List<User> moderators;
    private String country;
    private String communityImage;
    private String description;

    public Community(String name, String title, String creatorId, LocalDateTime createdDateTime, List<String> subscriberIds, List<String> postIds, String about, String sub, String type, GenreType genre, boolean joinable, boolean creatable, List<String> reviews, List<String> similarCommunityIds, List<CommunityRule> rules, int subscriberCount, int rankedSize, List<User> moderators, String country, String communityImage, String description) {
        this.name = name;
        this.title = title;
        this.creatorId = creatorId;
        this.createdDateTime = createdDateTime;
        this.subscriberIds = subscriberIds;
        this.postIds = postIds;
        this.about = about;
        this.sub = sub;
        this.type = type;
        this.genre = genre;
        this.joinable = joinable;
        this.creatable = creatable;
        this.reviews = reviews;
        this.similarCommunityIds = similarCommunityIds;
        this.rules = rules;
        this.subscriberCount = subscriberCount;
        this.rankedSize = rankedSize;
        this.moderators = moderators;
        this.country = country;
        this.communityImage = communityImage;
        this.description = description;
    }

    public <E> Community(String name, String title, String creatorId, LocalDateTime now, ArrayList<E> es, ArrayList<E> es1, String about, String sub, String type, GenreType genre, boolean joinable, boolean creatable, ArrayList<E> es2, ArrayList<E> es3, List<CommunityRule> rules, ArrayList<E> es4, String country, String communityImage, String description) {
    }
}