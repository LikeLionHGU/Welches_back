package likelion.summer.welches.post.presentation.response;

import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.user.application.dto.UserPostDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetAllResponse {
    private UserPostDto user;
    private LocalDateTime updatedDate;
    private Long id;
    private Boolean isApproved;

    public static PostGetAllResponse toResponse(Post post) {

        return PostGetAllResponse.builder()
                .user(UserPostDto.toResponsePost(post.getUser()))
                .isApproved(post.getIsAllowed())
                .updatedDate(post.getUpdatedDate())
                .id(post.getId())
                .build();
    }
}
