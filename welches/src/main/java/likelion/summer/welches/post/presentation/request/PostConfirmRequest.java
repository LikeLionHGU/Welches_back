package likelion.summer.welches.post.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostConfirmRequest {
    private Long id;
    private Boolean isAllowed;
    private String rejectedReason;
}
