package likelion.summer.welches.userApplication.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserApplicationApproveRequest {
    private String userId;
    private Long projectId;
}
