package likelion.summer.welches.post.presentation.response;

import likelion.summer.welches.post.domain.entity.Post;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostBookMarkResponse {
    private Long id;
    private String contents;

    public static PostBookMarkResponse toResponse(Post post) {
        return PostBookMarkResponse.builder()
                .id(post.getId())
                .contents(post.getContents())
                .build();

    }
}
