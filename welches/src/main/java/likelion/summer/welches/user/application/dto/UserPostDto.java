package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPostDto {
    private String id;
    private String name;

    public static UserPostDto toResponsePost(User user) {
        return UserPostDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
