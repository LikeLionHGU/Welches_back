package likelion.summer.welches.userBookMark.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBookMarkAddRequest {
    private String userId;
    private Long bookMarkId;
}
