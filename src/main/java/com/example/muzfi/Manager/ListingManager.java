package com.example.muzfi.Manager;

import com.example.muzfi.Dto.PostDto.ListingDetailsDto;
import com.example.muzfi.Dto.PostDto.ListingFeedDto;
import com.example.muzfi.Model.Post.Listing;
import org.springframework.stereotype.Component;

@Component
public class ListingManager {

    //Create ListingDetailsDto
    public ListingDetailsDto getListingDetailsDto(Listing listing) {
        ListingDetailsDto listingDetailsDto = new ListingDetailsDto();

        listingDetailsDto.setId(listing.getId());
        listingDetailsDto.setPostId(listing.getPostId());
        listingDetailsDto.setAuthorId(listing.getAuthorId());
        listingDetailsDto.setBrand(listing.getBrand());
        listingDetailsDto.setModel(listing.getModel());
        listingDetailsDto.setYear(listing.getYear());
        listingDetailsDto.setFormula(listing.getFormula());
        listingDetailsDto.setTitle(listing.getTitle());
        listingDetailsDto.setSubTitle(listing.getSubTitle());
        listingDetailsDto.setDescription(listing.getDescription());
        listingDetailsDto.setImages(listing.getImages());
        listingDetailsDto.setCondition(listing.getCondition());
        listingDetailsDto.setConditionDescription(listing.getConditionDescription());
        listingDetailsDto.setType(listing.getType());
        listingDetailsDto.setPrice(listing.getPrice());
        listingDetailsDto.setIs3PercentFromFinalSellingPrice(listing.getIs3PercentFromFinalSellingPrice());
        listingDetailsDto.setIsAcceptOffers(listing.getIsAcceptOffers());
        listingDetailsDto.setSize(listing.getSize());
        listingDetailsDto.setBidsCount(listing.getBidsCount());
        listingDetailsDto.setDeadline(listing.getDeadline());
        listingDetailsDto.setCreatedDateTime(listing.getCreatedDateTime());
        listingDetailsDto.setUpdatedDateTime(listing.getUpdatedDateTime());

        return listingDetailsDto;
    }

    public ListingFeedDto getListingFeedDto(Listing listing) {
        ListingFeedDto listingFeedDto = new ListingFeedDto();

        listingFeedDto.setId(listing.getId());
        listingFeedDto.setTitle(listing.getTitle());
        listingFeedDto.setSubTitle(listing.getSubTitle());
        listingFeedDto.setDescription(listing.getDescription());
        listingFeedDto.setCondition(listing.getCondition());
        listingFeedDto.setImages(listing.getImages());
        listingFeedDto.setPrice(listing.getPrice());
        listingFeedDto.setBidsCount(listing.getBidsCount());
        listingFeedDto.setDeadline(listing.getDeadline());
        listingFeedDto.setCreatedDateTime(listing.getCreatedDateTime());
        listingFeedDto.setUpdatedDateTime(listing.getUpdatedDateTime());

        return listingFeedDto;
    }
}
