package com.example.muzfi.Model;

import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Enums.GenreType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "gears")
public class Gear {
    @Id
    private String id;

    private String postId;

    private String authorId;

    private PostAuthorDto author;

    private String name;

    private Boolean addToFeed;

    private String brandName;

    private String model;

    private List<String>  photo;

    private String description;

    private String receiveMethod;

    private String duration;

    private String status;

    private Boolean isLiked;

    private Integer comments;

    private Integer likes;

    private Double sRate;

    private Double pRate;

    private Double vRate;

    private Double lRate;

    @NotNull
    private GenreType postCategory;
    @CreatedDate
    private LocalDateTime createdDateTime;

}

// You'll also need to define Review, Rating, Specs, PriceTrend classes.
