package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationDto {
    private String id;
    private String name;

    public static UserInformationDto toResponse(UserApplication userApplication) {
        return UserInformationDto.builder()
                .id(userApplication.getUser().getId())
                .name(userApplication.getUser().getName())
                .build();
    }

    public static UserInformationDto toResponse(UserProject userProject) {
        return UserInformationDto.builder()
                .id(userProject.getUser().getId())
                .name(userProject.getUser().getName())
                .build();
    }
}
