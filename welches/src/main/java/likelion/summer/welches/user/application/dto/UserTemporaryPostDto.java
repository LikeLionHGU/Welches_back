package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserTemporaryPostDto {
    private String id;
    private String name;

    public static UserTemporaryPostDto toReturn(User user) {
        return UserTemporaryPostDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
