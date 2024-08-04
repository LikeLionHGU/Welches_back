package likelion.summer.welches.communityPostComment.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.communityPostComment.application.service.CommunityPostCommentService;
import likelion.summer.welches.communityPostComment.presentation.request.CommunityPostCommentAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommunityPostCommentController {
    private final CommunityPostCommentService communityPostCommentService;
    private final JWTProvider jwtProvider;

    @PostMapping("/community/comment/add")
    public ResponseEntity<Long> addCommentToCommunity(HttpServletRequest request, @RequestBody CommunityPostCommentAddRequest commentAddRequest) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostCommentService.addComment(userId, commentAddRequest.getPostId(), commentAddRequest.getContents()));
    }

    @DeleteMapping("/community/comment/delete/{id}")
    public ResponseEntity<Long> deleteComment(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostCommentService.deleteComment(id));
    }
}
