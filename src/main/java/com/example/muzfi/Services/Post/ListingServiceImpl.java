package com.example.muzfi.Services.Post;

import com.example.muzfi.Dto.PostDto.*;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Manager.ListingManager;
import com.example.muzfi.Manager.PostManager;
import com.example.muzfi.Model.Post.Listing;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.Product;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.ListingRepository;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Services.ListingEventPublisher;
import com.example.muzfi.Services.ProductService;
import com.example.muzfi.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListingServiceImpl implements ListingService {

    private final PostRepository postRepository;

    private final ListingRepository listingRepository;

    private final UserService userService;

    private final ProductService productService;

    private final PostService postService;

    private final PostManager postManager;

    private final ListingManager listingManager;

    private final ListingEventPublisher listingEventPublisher;

    @Autowired
    public ListingServiceImpl(ProductService productService, PostService postService, PostRepository postRepository, ListingRepository listingRepository, UserService userService, PostManager postManager, ListingManager listingManager, ListingEventPublisher listingEventPublisher) {
        this.postRepository = postRepository;
        this.productService = productService;
        this.listingRepository = listingRepository;
        this.userService = userService;
        this.postManager = postManager;
        this.listingManager = listingManager;
        this.postService = postService;
        this.listingEventPublisher = listingEventPublisher;
    }

    //get listings - filtered draft listings
    @Override
    public Optional<List<ListingDetailsDto>> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        List<ListingDetailsDto> listingList = new ArrayList<>();

        for (Listing listing : listings) {
            Optional<Post> postOpt = postRepository.findById(listing.getPostId());

            if (postOpt.isPresent()) {
                Post post = postOpt.get();

                if (post.getIsDraft() == null || post.getIsDraft().equals(false)) {
                    ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                    listingList.add(dto);
                }
            }
        }

        if (listingList.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(listingList);
    }

    @Override
    public Optional<ListingDetailsDto> getListingById(String listingId) {
        Optional<Listing> listingOptional = listingRepository.findById(listingId);

        if (listingOptional.isPresent()) {
            Listing listing = listingOptional.get();
            ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(dto.getAuthorId());
            PostAuthorDto author = authorOptional.get();
            Optional<PostDetailsDto> postOptional = postService.getPostById(dto.getPostId());
            dto.setIsLiked(postOptional.get().getIsLiked());
            dto.setLikes(postOptional.get().getLikes());
            dto.setComments(postOptional.get().getComments());
            dto.setAuthor(author);
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    //Get user listings - filtered draft listings
    @Override
    public Optional<List<ListingDetailsDto>> getListingsByUserId(String userId) {
        List<Listing> listings = listingRepository.findAllByAuthorId(userId);

        if (!listings.isEmpty()) {
            List<ListingDetailsDto> listingDtoList = new ArrayList<>();

            for (Listing listing : listings) {
                Optional<Post> postOpt = postRepository.findById(listing.getPostId());

                if (postOpt.isPresent()) {
                    Post post = postOpt.get();

                    if (post.getIsDraft() == null || post.getIsDraft().equals(false)) {
                        ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                        listingDtoList.add(dto);
                    }
                }
            }

            return Optional.of(listingDtoList);
        } else {
            return Optional.empty();
        }
    }

    //Get draft user listings
    @Override
    public Optional<List<ListingDetailsDto>> getDraftListingsByUserId(String userId) {
        List<Listing> listings = listingRepository.findAllByAuthorId(userId);

        if (!listings.isEmpty()) {
            List<ListingDetailsDto> listingDtoList = new ArrayList<>();

            for (Listing listing : listings) {
                Optional<Post> postOpt = postRepository.findById(listing.getPostId());

                if (postOpt.isPresent()) {
                    Post post = postOpt.get();

                    if (post.getIsDraft() != null && post.getIsDraft().equals(true)) {
                        ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                        listingDtoList.add(dto);
                    }
                }
            }

            return Optional.of(listingDtoList);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<PostDetailsDto> createListing(ListingCreateDto listingDto) {
        Optional<User> user = userService.getUserById(listingDto.getAuthorId());
        //create post
        Post newPost = new Post();
        newPost.setAuthorId(listingDto.getAuthorId());
        newPost.setPostType(PostType.PROD_SALE);
        newPost.setIsEnablePostReplyNotification(listingDto.getIsEnablePostReplyNotification());
        newPost.setCreatedDateTime(LocalDateTime.now());
        newPost.setUpdatedDateTime(LocalDateTime.now());
        newPost.setCommunity(listingDto.getCommunity());
        newPost.setIsDraft(listingDto.getIsDraft());

        //create listing
        Listing newListing = new Listing();
        newListing.setAuthorId(listingDto.getAuthorId());
        newListing.setBrand(listingDto.getBrand());
        newListing.setModel(listingDto.getModel());
        newListing.setYear(listingDto.getYear());
        newListing.setSize(listingDto.getSize());
        newListing.setTitle(listingDto.getTitle());
        newListing.setSubTitle(listingDto.getSubTitle());
        newListing.setDescription(listingDto.getDescription());
        newListing.setFormula(listingDto.getFormula());
        newListing.setImages(listingDto.getImages());
        newListing.setCreatedDateTime(LocalDateTime.now());
        newListing.setUpdatedDateTime(LocalDateTime.now());
        newListing.setCondition(listingDto.getCondition());
        newListing.setConditionDescription(listingDto.getConditionDescription());
        newListing.setType(listingDto.getType());
        newListing.setPrice(listingDto.getPrice());
        newListing.setIs3PercentFromFinalSellingPrice(listingDto.getIs3PercentFromFinalSellingPrice());
        newListing.setIsAcceptOffers(listingDto.getIsAcceptOffers());
        newListing.setDeadline(listingDto.getDeadline());

        //create product
        Product newProduct = new Product();
        newProduct.setName(listingDto.getTitle());
        newProduct.setSellerId(listingDto.getAuthorId());
        newProduct.setPrice(listingDto.getPrice());
        newProduct.setBrandId(listingDto.getBrand());
        newProduct.setCondition(listingDto.getCondition());
        newProduct.setFormula(listingDto.getFormula());
        newProduct.setSize(listingDto.getSize());
        newProduct.setYear(listingDto.getYear());
        newProduct.setDescription(listingDto.getDescription());
        newProduct.setStatus("active");
        newProduct.setCategory(listingDto.getType());
        newProduct.setCreatedDate(LocalDateTime.now());
        newProduct.setSellerEmail("paugustin03@gmail.com");

        //save post and listing

        Post post = postRepository.save(newPost);
        Listing listing = listingRepository.save(newListing);
        newProduct.setPostId(listing.getId());
        productService.saveProduct(newProduct);

        //update post and listing with ids
        post.setPostTypeId(listing.getId());
        listing.setPostId(post.getId());

        User existingUser = user.get();
        existingUser.setMuzPoints(existingUser.getMuzPoints() + 25);
        existingUser.setNoOfSales(existingUser.getNoOfSales() + 1);
        existingUser.setNoOfPosts(existingUser.getNoOfPosts() + 1);
        userService.updateUser(existingUser);

        Post postUpdated = postRepository.save(post);
        Listing listingUpdated = listingRepository.save(listing);

        //return created post
        ListingDetailsDto listingDetailsDto = listingManager.getListingDetailsDto(listingUpdated);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, listingDetailsDto, authorOptional.get());

        //publish the listing created event
//        listingEventPublisher.publishListingCreatedEvent(listingUpdated.getId());

        return Optional.ofNullable(postDetailsDto);
    }

    @Override
    public Optional<PostDetailsDto> updateListing(ListingUpdateDto updateDto) {
        Optional<Listing> listingOpt = listingRepository.findById(updateDto.getId());
        Optional<Post> postOpt = postRepository.findById(updateDto.getPostId());

        if (listingOpt.isEmpty() || postOpt.isEmpty()) {
            return Optional.empty();
        }

        Listing listing = listingOpt.get();
        Post post = postOpt.get();

        //set listing
        listing.setBrand(updateDto.getBrand());
        listing.setModel(updateDto.getModel());
        listing.setYear(updateDto.getYear());
        listing.setSize(updateDto.getSize());
        listing.setTitle(updateDto.getTitle());
        listing.setSubTitle(updateDto.getSubTitle());
        listing.setDescription(updateDto.getDescription());
        listing.setFormula(updateDto.getFormula());
        listing.setImages(updateDto.getImages());
        listing.setCondition(updateDto.getCondition());
        listing.setConditionDescription(updateDto.getConditionDescription());
        listing.setType(updateDto.getType());
        listing.setPrice(updateDto.getPrice());
        listing.setIs3PercentFromFinalSellingPrice(updateDto.getIs3PercentFromFinalSellingPrice());
        listing.setIsAcceptOffers(updateDto.getIsAcceptOffers());
        listing.setDeadline(updateDto.getDeadline());
        listing.setUpdatedDateTime(LocalDateTime.now());

        post.setUpdatedDateTime(LocalDateTime.now());

        Post postUpdated = postRepository.save(post);
        Listing listingUpdated = listingRepository.save(listing);

        ListingDetailsDto listingDetailsDto = listingManager.getListingDetailsDto(listingUpdated);
        Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(post.getAuthorId());
        PostDetailsDto postDetailsDto = postManager.getPostDetailsDto(postUpdated, listingDetailsDto, authorOptional.get());

        return Optional.of(postDetailsDto);
    }


    //Including draft posts
    @Override
    public Optional<List<ListingDetailsDto>> getListingsCreatedByUserInCurrentMonth(String userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfMonth = now.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime startOfNextMonth = startOfMonth.plusMonths(1);

        List<Listing> listings = listingRepository.findListingsCreatedByUserInCurrentMonth(userId, startOfMonth, startOfNextMonth);

        if (!listings.isEmpty()) {
            List<ListingDetailsDto> listingDtoList = new ArrayList<>();

            for (Listing listing : listings) {
                ListingDetailsDto dto = listingManager.getListingDetailsDto(listing);
                listingDtoList.add(dto);
            }

            return Optional.of(listingDtoList);
        } else {
            return Optional.empty();
        }
    }

}
