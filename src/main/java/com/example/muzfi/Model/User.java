package com.example.muzfi.Model;

import com.example.muzfi.Enums.BackgroundType;
import com.example.muzfi.Enums.UserGender;
import com.example.muzfi.Enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String oktaId;

    private String email;

    private String password;

    private String userName;

    private String firstName;

    private String lastName;

    private String displayName;

    private UserGender gender = UserGender.NONE;

    private List<UserRole> role;

    private String description;

    private LocalDate birthDate;

    private String location;

    private BackgroundType background = BackgroundType.CASUAL;

    private Boolean isShowsUpInSearchResults = true;

    private String profilePicUri;

    private String bannerImageUri;

    private List<SocialLink> socialLinks;

    private Set<String> followersUserIds;

    private Set<String> followingsUserIds;

    private int muzPoints;

    private int noOfPosts;

    private int noOfGears;

    private int noOfSales;

    private double sellerRatings = 5.0;

    private double buyerRatings = 5.0;

    private Set<String> blockedUserIds;

    private Set<String> blockedByUserIds;

    //TODO: update with user date and time
    private LocalDateTime createdDateTime = LocalDateTime.now();

    private LocalDateTime lastUpdatedDateTime;
    private boolean banned;
    private String banReason;
    private LocalDate banStartDate;
    private LocalDate banEndDate;
    private boolean approved;
}
