package likelion.summer.welches.bookMark.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkUpdateRequest {
    private String name;
    private Boolean isSameTime;
    private Boolean isShared;
}
