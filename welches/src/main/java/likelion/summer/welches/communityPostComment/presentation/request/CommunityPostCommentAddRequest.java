package likelion.summer.welches.communityPostComment.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityPostCommentAddRequest {
    private String contents;
    private Long postId;
}
