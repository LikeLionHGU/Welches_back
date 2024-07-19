package likelion.summer.welches.communityPostLike.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.communityPostLike.application.service.CommunityPostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityPostLikeController {
    private final CommunityPostLikeService communityPostLikeService;
    private final JWTProvider jwtProvider;

    @PostMapping("/community/post/like/switch/{id}")
    public ResponseEntity<Long> changeLike(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostLikeService.changeLike(userId, id));
    }
}
