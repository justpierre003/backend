package com.example.muzfi.Services;
import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Enums.GenreType;
import com.example.muzfi.Model.Community;
import com.example.muzfi.Model.CommunityRule;
import com.example.muzfi.Model.CommunitySettingsUpdate;
import com.example.muzfi.Model.User;
import com.example.muzfi.Repository.CommunityRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailConfirmationService;
import com.example.muzfi.Services.User.UserService;
import com.example.muzfi.exception.NotFoundException;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;
    private final UserService userService;
    private final EmailConfirmationService emailConfirmationService;
    private final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository, UserService userService, EmailConfirmationService emailConfirmationService) {
        this.communityRepository = communityRepository;
        this.userService = userService;
        this.emailConfirmationService = emailConfirmationService;
    }

    @Override
    public Community createCommunity(CommunityDto communityDto) throws MessagingException   {

        try {
            Community community = new Community();
            community.setName(communityDto.getName());
            community.setTitle(communityDto.getTitle());
            community.setCreatorId(communityDto.getCreatorId());
            community.setCreatedDateTime(LocalDateTime.now());
            community.setType(communityDto.getType());
            community.setGenre(communityDto.getGenre());
            community.setDescription("Welcome to Pairrit! Making social shopping fun and safe!");
            log.debug("Comm Name:"+communityDto.getName());
            List<CommunityRule> ruleList = new ArrayList<>();
            List<User> modList = new ArrayList<>();
            Optional<User> newMod = userService.getUserById(communityDto.getCreatorId());
            modList.add(newMod.get());
            community.setModerators(modList);
            community.setRules(ruleList);
            Community savedCommunity = communityRepository.save(community);

            // Optionally, you can send a confirmation email here if needed
            try {
                emailConfirmationService.sendCommunityCreatedConfirmation(communityDto.getCreatorId(), communityDto.getName());
            } catch (MessagingException e) {
                log.error("Error sending confirmation email", e);
            }

            return savedCommunity;
        } catch (Exception e) {
            // Log an error during signup
            log.error("Error during create community", e);
            // You might choose to throw an exception here if you want to propagate the error
            // For simplicity, this example does not rethrow the exception
            return null;
        }
    }
    @Override
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @Override
    public List<Community> getCommunitiesByGenre(GenreType genre) {

        List<Community> communities = communityRepository.findByGenre(genre);

        return communities;
    }

    @Override
    public List<Community> getCommunitiesByUserId(String userId) {

        List<Community> communities = communityRepository.findByCreatorId(userId);

        return communities;
    }

    @Override
    public Community getCommunityByName(String name) {

        Community community = communityRepository.findByName(name);
        log.debug("comm name found" + community.getName());
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + name);
        }
        return community;
    }

    @Override
    public void addUserToCommunity(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getSubscriberIds().add(userId);
        communityRepository.save(community);
    }

    private CommunityDto mapToCommunityDto(Community community) {
        CommunityDto dto = new CommunityDto();
        dto.setId(community.getId());
        dto.setName(community.getName());
        dto.setTitle(community.getTitle());
        dto.setCreatorId(community.getCreatorId());
        dto.setAbout(community.getAbout());
        dto.setSubscriberCount(community.getSubscriberIds().size());
        dto.setPostCount(community.getPostIds().size());
        return dto;
    }

    @Override
    public List<CommunityDto> getSimilarCommunities(String communityName) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }

        List<Community> similarCommunities = communityRepository.findByTypeAndGenre(
                community.getType(),
                community.getGenre()
        );

        List<CommunityDto> similarCommunityDtos = similarCommunities.stream()
                .filter(c -> !c.getName().equals(community.getName()))
                .map(this::mapToCommunityDto)
                .collect(Collectors.toList());

        return similarCommunityDtos;
    }

    @Override
    public void addRule(String communityName, String rule) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        List<CommunityRule> ruleList = community.getRules();
        CommunityRule newRule = new CommunityRule();
        newRule.setRule(rule);
        ruleList.add(newRule);
        community.setRules(ruleList);
        communityRepository.save(community);
    }

    @Override
    public void addModerator(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        List<User> modList = community.getModerators();
        Optional<User> newMod = userService.getUserById(userId);
        modList.add(newMod.get());
        community.setModerators(modList);
        communityRepository.save(community);
    }

    @Override
    public Community updateCommunity(String id, CommunitySettingsUpdate communitySettingsUpdate) {
            Community existingCommunity = communityRepository.findById(id).orElse(null);
            if (existingCommunity == null) {
                return null;
            }
            existingCommunity.setName(communitySettingsUpdate.getCommunityName());
            existingCommunity.setSub(communitySettingsUpdate.getSubCategory());
            existingCommunity.setAbout(communitySettingsUpdate.getDescription());
            existingCommunity.setCountry(communitySettingsUpdate.getCountry());
            existingCommunity.setCommunityImage(communitySettingsUpdate.getCommunityImage());
            existingCommunity.setType(communitySettingsUpdate.getPrivacy());
            return communityRepository.save(existingCommunity);
    }

    @Override
    public void removeModerator(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getModerators().remove(userId);
        communityRepository.save(community);
    }



    @Override
    public void addMember(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getSubscriberIds().add(userId);
        communityRepository.save(community);
    }

    @Override
    public void removeMember(String communityName, String userId) {
        Community community = communityRepository.findByName(communityName);
        if (community == null) {
            throw new NotFoundException("Community not found with name: " + communityName);
        }
        community.getSubscriberIds().remove(userId);
        communityRepository.save(community);
    }

    @Override
    public void restrictMember(String communityName, String userId) {
        // Implement logic to restrict a member in the community
    }



    @Override
    public void deleteCommunity(String id) {
        communityRepository.deleteById(id);
    }
}
