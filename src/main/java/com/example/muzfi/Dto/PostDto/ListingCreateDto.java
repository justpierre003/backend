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
public class ListingCreateDto extends PostCreateDto {

    private String brand;

    private String email;

    private String model;

    private Integer year;

    private String title;

    private Double size;

    private Formula formula;

    private List<String> images;

    private ProductCondition condition;

    private String conditionDescription;

//    private boolean soaleAsDescribe;

    private String type;

//    private List<DeliverMethod> deliverMethod;
//
//    private ProductShippingDetails shippingDetails;

    private Boolean offerShipping;

    private BigDecimal price;

    private Boolean is3PercentFromFinalSellingPrice;

    private Boolean isAcceptOffers;

//    private BigDecimal bumpRate;

    private LocalDateTime deadline;

    private String review;

}
