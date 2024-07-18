package likelion.summer.welches.login.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GoogleResponse {
    private String token;
    private int userId;
    private String accessToken;
    private String tokenType;
}
