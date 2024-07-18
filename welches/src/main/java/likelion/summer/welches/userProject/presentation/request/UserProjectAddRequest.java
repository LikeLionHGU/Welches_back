package likelion.summer.welches.userProject.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProjectAddRequest {
    private String userId;
    private Long projectId;
}
