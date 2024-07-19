package likelion.summer.welches.communityPostCommentLike.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.communityPostCommentLike.application.service.CommunityPostCommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommunityPostCommentLikeController {
    private final CommunityPostCommentLikeService communityPostCommentLikeService;
    private final JWTProvider jwtProvider;

    @PostMapping("/community/comment/like/switch/{id}")
    public ResponseEntity<Long> changeLike(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostCommentLikeService.changeLike(userId, id));
    }
}
