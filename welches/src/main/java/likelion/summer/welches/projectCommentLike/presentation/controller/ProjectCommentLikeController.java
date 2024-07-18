package likelion.summer.welches.projectCommentLike.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.projectCommentLike.application.service.ProjectCommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectCommentLikeController {
    private final ProjectCommentLikeService projectCommentLikeService;
    private final JWTProvider jwtProvider;

    @PostMapping("/project/comment/like/{id}")
    public ResponseEntity<Long> changeProjectCommentLike(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        projectCommentLikeService.changeProjectCommentLike(userId, id);

        return ResponseEntity.ok(id);
    }
}
