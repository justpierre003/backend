package com.example.muzfi.Dto.UserDto;

import com.example.muzfi.Enums.BackgroundType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDto {
    private String firstName;

    private String lastName;

    private String displayName;

    private String description;

    private String location;

    private Boolean isShowsUpInSearchResults;

    private BackgroundType background;

    private String profilePicUri;

    private LocalDate birthDate;
}
