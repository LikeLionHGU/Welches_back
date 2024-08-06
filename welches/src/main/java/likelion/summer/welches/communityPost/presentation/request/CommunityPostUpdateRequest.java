package likelion.summer.welches.communityPost.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityPostUpdateRequest {
    private String title;
    private String contents;
    private Long postId;
}
