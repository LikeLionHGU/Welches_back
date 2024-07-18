package likelion.summer.welches.userApplication.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserApplicationDeleteRequest {
    private String userId;
    private Long projectId;
}
