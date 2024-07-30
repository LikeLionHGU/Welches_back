package likelion.summer.welches.user.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.user.application.service.UserService;
import likelion.summer.welches.user.presentation.response.UserGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTProvider jwtProvider;

    @GetMapping("/user/info")
    public ResponseEntity<UserGetResponse> getUserInfo(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(userService.getUserInfo(userId));
    }
}
