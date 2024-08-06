package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommunityResponse {
    private String id;
    private String name;
    private String profile;

    public static UserCommunityResponse toResponse(User user) {
        return UserCommunityResponse.builder()
                .id(user.getId())
                .profile(user.getProfileImageAddress())
                .name(user.getName())
                .build();
    }
}
