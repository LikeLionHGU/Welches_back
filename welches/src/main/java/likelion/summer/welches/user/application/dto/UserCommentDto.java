package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentDto {
    private String id;
    private String name;
    private String profile;

    public static UserCommentDto toResponse(User user) {
        return UserCommentDto.builder()
                .id(user.getId())
                .profile(user.getProfileImageAddress())
                .name(user.getName())
                .build();
    }
}
