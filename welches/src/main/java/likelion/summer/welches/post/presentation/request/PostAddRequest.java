package likelion.summer.welches.post.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAddRequest {
    private String contents;
    private Long bookMarkId;
}
