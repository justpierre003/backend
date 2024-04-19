package com.example.muzfi.Services;

import com.example.muzfi.Dto.PostDto.GearCreateDto;
import com.example.muzfi.Dto.PostDto.PostAuthorDto;
import com.example.muzfi.Dto.PostDto.PostDetailsDto;
import com.example.muzfi.Enums.PostType;
import com.example.muzfi.Model.Gear;
import com.example.muzfi.Model.Post.Post;
import com.example.muzfi.Model.Post.Topic;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.GearRepository;
import com.example.muzfi.Repository.PostRepository;
import com.example.muzfi.Repository.UserRepository;
import com.example.muzfi.Services.Post.PostService;
import com.example.muzfi.Services.User.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GearServiceImpl implements GearService{
    private GearRepository gearRepository;
    private final PostRepository postRepository;
    private UserRepository userRepository;
    private final UserService userService;
    private final PostService postService;
    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final GearRoomService gearRoomService;

    @Override
    public Optional<List<Gear>> getAllGears() {
        return Optional.empty();
    }

    @Override
    public Optional<Gear> getGearById(String gearId) {
        Optional<Gear> gearOptional = gearRepository.findById(gearId);

        if (gearOptional.isPresent()) {
            Gear gear = gearOptional.get();
            Optional<PostAuthorDto> authorOptional = userService.getPostAuthor(gear.getAuthorId());
            PostAuthorDto author = authorOptional.get();
            Optional<PostDetailsDto> postOptional = postService.getPostById(gear.getPostId());
            gear.setIsLiked(postOptional.get().getIsLiked());
            gear.setLikes(postOptional.get().getLikes());
            gear.setComments(postOptional.get().getComments());
            gear.setAuthor(author);
            return Optional.of(gear);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Gear createGear(GearCreateDto gear) {
        // Add any validation or business logic before saving the gear
        try{
            Gear newGear = new Gear();
            newGear.setAuthorId(gear.getAuthorId());
            newGear.setBrandName(gear.getBrandName());
            newGear.setModel(gear.getModel());
            newGear.setDescription(gear.getDescription());
            newGear.setSRate(gear.getSRate());
            newGear.setPRate(gear.getPRate());
            newGear.setVRate(gear.getVRate());
            newGear.setLRate(gear.getLRate());
            newGear.setDuration(gear.getDuration());
            newGear.setReceiveMethod(gear.getReceiveMethod());
            newGear.setStatus(gear.getStatus());
            newGear.setPostCategory(gear.getPostCategory());
            newGear.setAddToFeed(gear.getAddToFeed());
            newGear.setCreatedDateTime(gear.getCreatedDateTime());
            Optional<User> userOptional = userRepository.findById(gear.getAuthorId());
            if (userOptional.isPresent()) {
                User existingUser = userOptional.get();
                existingUser.setMuzPoints(existingUser.getMuzPoints() + 20);
                existingUser.setNoOfGears(existingUser.getNoOfGears() + 1);
                existingUser.setNoOfPosts(existingUser.getNoOfPosts() + 1);
                userRepository.save(existingUser);
            }
            Gear savedGear = gearRepository.save(newGear);
            gearRoomService.addGear(newGear);
            // Additional validation or business logic can be added here based on your requirements
            if (gear.getAddToFeed() == true){
                Post newPost = new Post();
                newPost.setAuthorId(gear.getAuthorId());
                newPost.setPostType(PostType.PROD_GEAR);
                newPost.setIsEnablePostReplyNotification(gear.getIsEnablePostReplyNotification());
                newPost.setCreatedDateTime(gear.getCreatedDateTime());
                newPost.setIsDraft(gear.getIsDraft());
                newPost.setCommunity(gear.getCommunity());
                newPost.setPostCategory(gear.getPostCategory());
                Post post = postRepository.save(newPost);
                post.setPostTypeId(savedGear.getId());
                savedGear.setPostId(post.getId());
                postRepository.save(post);
                gearRepository.save(savedGear);

            }
            return savedGear;
        }catch (Exception e) {
            // Log an error during signup
            log.error("Error during create Gear", e);
            // You might choose to throw an exception here if you want to propagate the error
            // For simplicity, this example does not rethrow the exception
            return null;
        }

        // Check if the gear with the same name already exists
//        Optional<Gear> existingGear = gearRepository.findByName(gear.getName());
//        if (existingGear.isPresent()) {
//            // You may want to throw an exception or handle this case differently based on your requirements
//            throw new IllegalArgumentException("Gear with the same name already exists");
//        }


    }


    @Override
    public Gear updateGear(String gearId, Gear gear) {
        return null;
    }

    @Override
    public List<Gear> searchGears(String searchTerm, String category) {
        if (category != null && !category.isEmpty()) {
            return gearRepository.findByNameContainingAndPostCategory(searchTerm, category);
        } else {
            return gearRepository.findByNameContainingIgnoreCase(searchTerm);
        }
    }

    @Override
    public List<Gear> getLatestGears(int limit) {
        // Assuming Gear entity has a field like 'createdDate' or 'lastUpdatedDate'
        // Replace 'findTopNByOrderByCreatedDateDesc' with the actual repository method
        return gearRepository.findTopByOrderByCreatedDateTimeDesc(limit);
    }

    @Override
    public void deleteGear(String gearId) {
        gearRepository.deleteById(gearId);
    }
}
