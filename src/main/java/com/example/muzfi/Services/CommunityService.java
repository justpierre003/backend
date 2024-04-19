package com.example.muzfi.Services;

import com.example.muzfi.Dto.CommunityDto;
import com.example.muzfi.Enums.GenreType;
import jakarta.mail.MessagingException;
import com.example.muzfi.Model.Community;
import com.example.muzfi.Model.CommunitySettingsUpdate;

import java.util.List;

public interface CommunityService {

    Community createCommunity(CommunityDto communityDto) throws MessagingException;

    List<Community> getAllCommunities();

    List<Community> getCommunitiesByGenre(GenreType genre);

    List<Community> getCommunitiesByUserId(String userId);

    Community getCommunityByName(String name);

    void addUserToCommunity(String communityName, String userId);

    List<CommunityDto> getSimilarCommunities(String communityName);

    void addRule(String communityName, String rule);

//    void removeRule(String communityName, String ruleId);

    void removeModerator(String communityName, String userId);

    void addMember(String communityName, String userId);

    void removeMember(String communityName, String userId);

    void restrictMember(String communityName, String userId);

    void addModerator(String communityName, String userId);

    Community updateCommunity(String id, CommunitySettingsUpdate communitySettingsUpdate);

    void deleteCommunity(String id);

}
