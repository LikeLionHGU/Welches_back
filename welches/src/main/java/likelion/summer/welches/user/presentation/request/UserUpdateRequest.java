package likelion.summer.welches.user.presentation.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class UserUpdateRequest {
    private UserRequest userRequest;
    private MultipartFile profile;
    private MultipartFile back;
}
