package likelion.summer.welches.temporaryPost.presentation.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemporaryPostAddRequest {
    private String contents;
    private Long bookMarkId;
}
