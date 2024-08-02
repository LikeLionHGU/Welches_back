package likelion.summer.welches.bookMark.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMarkAddRequest {
    private String name;
    private Boolean isSameTime;
    private Boolean isShared;
    private Long projectId;
}
