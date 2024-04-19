package com.example.muzfi.Model.Post;

import com.example.muzfi.Enums.DeliverMethod;
import com.example.muzfi.Enums.Formula;
import com.example.muzfi.Enums.ProductCondition;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "listings")
public class Listing {
    @Id
    private String id;

    private String postId;

    private String authorId;
    private String authorEmail;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    private Integer year;

    private Double size;

    @NotNull
    private String title;

    private String subTitle;

    @NotNull
    private String description;

    private Formula formula;

    @NotNull
    private List<String> images;

    @NotNull
    private ProductCondition condition;

    @NotNull
    private String conditionDescription;

    private String type;

//    @NotNull
//    private List<DeliverMethod> deliverMethod;
//
//    private ProductShippingDetails shippingDetails;

    @NotNull
    private BigDecimal price;

    private Boolean is3PercentFromFinalSellingPrice;

    private Boolean isAcceptOffers;

//    private BigDecimal bumpRate;

    private int bidsCount;

    private LocalDateTime deadline;

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;
}
