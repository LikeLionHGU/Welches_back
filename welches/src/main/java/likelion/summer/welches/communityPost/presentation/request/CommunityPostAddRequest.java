package likelion.summer.welches.communityPost.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityPostAddRequest {
    Long projectId;
    String contents;

    String title;
}
