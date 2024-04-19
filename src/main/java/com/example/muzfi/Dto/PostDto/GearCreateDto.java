package com.example.muzfi.Dto.PostDto;

import com.example.muzfi.Enums.TopicType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GearCreateDto extends PostCreateDto{


    private String brandName;

    private String model;

    private Boolean addToFeed;

    private String receiveMethod;

    private String duration;

    private String status;

    private Double sRate;

    private Double pRate;

    private Double vRate;

    private Double lRate;

}
