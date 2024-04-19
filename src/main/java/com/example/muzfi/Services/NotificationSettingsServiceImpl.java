package com.example.muzfi.Services;

import com.example.muzfi.Model.NotificationSettings;
import com.example.muzfi.Repository.NotificationSettingsRepository;
import com.example.muzfi.Services.EmailConfirmationService.EmailNotification.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationSettingsServiceImpl implements NotificationSettingsService{
    private final NotificationSettingsRepository notificationSettingsRepository;

    @Override
    public NotificationSettings createNotificationSettings(NotificationSettings settings) {
//        EmailNotificationService emailNotificationService = new EmailNotificationService();
//        emailNotificationService.sendUncheckedNotificationsAlert(settings.getUserId(),1);
        return notificationSettingsRepository.save(settings);

    }

    @Override
    public Optional<NotificationSettings> getNotificationSettingsByUserId(String userId) {
        return notificationSettingsRepository.findByUserId(userId);
    }

    @Override
    public Optional<NotificationSettings> updateNotificationSettings(NotificationSettings settings) {
        Optional<NotificationSettings> notificationSettingsOptional = notificationSettingsRepository.findByUserId(settings.getUserId());

        if (notificationSettingsOptional.isPresent()) {
            NotificationSettings existingSettings = notificationSettingsOptional.get();
            if (settings.getInboxMessages() != null) { existingSettings.setInboxMessages(settings.getInboxMessages());}
            if (settings.getChatMessages()!= null) {existingSettings.setChatMessages(settings.getChatMessages());}
            if (settings.getActivity() != null) {existingSettings.setActivity(settings.getActivity());}
            if (settings.getMentions() != null) {existingSettings.setMentions(settings.getMentions());}
            if (settings.getCommentsOnPosts() != null) {existingSettings.setCommentsOnPosts(settings.getCommentsOnPosts());}
            if (settings.getRepliesToComments() != null) {existingSettings.setRepliesToComments(settings.getRepliesToComments());}
            if (settings.getOffersMadeAccepted() != null) {existingSettings.setOffersMadeAccepted(settings.getOffersMadeAccepted());}
            if (settings.getOrders() != null) {existingSettings.setOrders(settings.getOrders());}


            NotificationSettings updatedSettings = notificationSettingsRepository.save(existingSettings);
            return Optional.of(updatedSettings);
        } else {
            return Optional.empty();
        }
    }
}
