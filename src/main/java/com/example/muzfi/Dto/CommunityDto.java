package com.example.muzfi.Dto;

import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Model.CommunityRule;
import com.example.muzfi.Model.User;
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
public class CommunityDto {

    private String id;
    private String name;
    private String title;
    private String creatorId;
    private String about;
    private int subscriberCount;
    private int postCount;
    private String sub;
    private String type;
    private GenreType genre;
    private boolean joinable;
    private boolean creatable;
    private List<String> reviews;
    private List<String> similarCommunityIds;
    private CommunityRule rules;
    private int rankedSize;
    private List<User> moderators;
    private String country;
    private String communityImage;
    private String description;
}
