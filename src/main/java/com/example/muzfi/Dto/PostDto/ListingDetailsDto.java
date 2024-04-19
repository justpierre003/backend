package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.DeliverMethod;
import com.example.muzfi.Enums.Formula;
import com.example.muzfi.Enums.ProductCondition;
import com.example.muzfi.Model.Post.ProductShippingDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ListingDetailsDto {

    private String id;

    private String postId;

    private String authorId;
    private String authorEmail;

    private PostAuthorDto author;

    private String brand;

    private String model;

    private Integer year;

    private Double size;

    private String title;

    private String subTitle;

    private String description;

    private Boolean isLiked;

    private Integer comments;

    private Integer likes;

    private List<String> images;

    private ProductCondition condition;

    private String conditionDescription;

    private String type;

    private Formula formula;

//    private List<DeliverMethod> deliverMethod;
//
//    private ProductShippingDetails shippingDetails;

    private BigDecimal price;

    private Boolean is3PercentFromFinalSellingPrice;

    private Boolean isAcceptOffers;

//    private BigDecimal bumpRate;

    private int bidsCount;

    private LocalDateTime deadline;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    private String review;
}
