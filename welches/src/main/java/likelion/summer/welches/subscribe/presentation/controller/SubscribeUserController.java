package likelion.summer.welches.subscribe.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.subscribe.application.service.SubscribeUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscribeUserController {
    private final SubscribeUserService subscribeUserService;
    private final JWTProvider jwtProvider;

    @PostMapping("/subscribe/add/{id}")
    public ResponseEntity<Long> addSubscribeUser(HttpServletRequest request, @PathVariable String id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(subscribeUserService.addSubscribeUser(userId, id));
    }
}
