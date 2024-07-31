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

    public static UserCommunityResponse toResponse(User user) {
        return UserCommunityResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
