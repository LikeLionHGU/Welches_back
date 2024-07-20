package likelion.summer.welches.post.presentation.response;

import likelion.summer.welches.post.domain.entity.Post;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGetResponse {

    private Long id;

    private String contents;

    private Boolean isConfirmed;

    private Boolean isAllowed;

    private String rejectedReason;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    public static PostGetResponse toResponse(Post post) {
        return PostGetResponse.builder()
                .id(post.getId())
                .contents(post.getContents())
                .isConfirmed(post.getIsConfirmed())
                .isAllowed(post.getIsAllowed())
                .rejectedReason(post.getRejectedReason())
                .createdDate(post.getCreatedDate())
                .updatedDate(post.getUpdatedDate())
                .build();
    }
}
