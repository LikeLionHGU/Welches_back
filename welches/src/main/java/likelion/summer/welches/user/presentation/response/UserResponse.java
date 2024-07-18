package likelion.summer.welches.user.presentation.response;

import likelion.summer.welches.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponse {
    private String name;
    private String userId;
    private String email;
    private String token;


    public static UserResponse toResponse(User user, String token) {
        return UserResponse.builder()
                .name(user.getName())
                .userId(user.getId())
                .email(user.getEmail())
                .token(token)
                .build();
    }
}
