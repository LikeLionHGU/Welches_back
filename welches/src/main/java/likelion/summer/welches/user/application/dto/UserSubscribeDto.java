package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.subscribe.domain.entity.SubscribeUser;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscribeDto {
    private String userId;
    private String name;


    public static UserSubscribeDto toResponse(User user) {
        return UserSubscribeDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .build();
    }
}
