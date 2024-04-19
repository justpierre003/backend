package com.example.muzfi.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@Getter
@Setter
public class NotificationSettings {
    @Id
    private String userId;
    private Boolean inboxMessages;
    private Boolean chatMessages;
    private Boolean activity;
    private Boolean mentions;
    private Boolean commentsOnPosts;
    private Boolean repliesToComments;
    private Boolean offersMadeAccepted;
    private Boolean orders;
}
