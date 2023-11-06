package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.DeliverMethod;
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
public class ListingUpdateDto {

    private String id;

    private String postId;

    private String brand;

    private String model;

    private String year;

    private String finish;

    private String title;

    private String subTitle;

    private String description;

    private Boolean isHandMade;

    private List<String> images;

    private String condition;

    private String conditionDescription;

    private boolean saleAsDescribe;

    private String youTubeLink;

    private List<DeliverMethod> deliverMethod;

    private ProductShippingDetails shippingDetails;

    private Boolean offerShipping;

    private BigDecimal price;

    private Boolean is3PercentFromFinalSellingPrice;

    private Boolean isAcceptOffers;

    private BigDecimal bumpRate;

    private LocalDateTime deadline;

    private List<String> tags;
}
