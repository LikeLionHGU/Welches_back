package likelion.summer.welches.projectLike.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.projectLike.application.service.ProjectLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectLikeController {
    private final ProjectLikeService projectLikeService;
    private final JWTProvider jwtProvider;

    @PostMapping("/project/like/switch/{id}")
    public ResponseEntity<Long> changeLikeInformation(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);
        projectLikeService.translateLikeInformation(userId, id);

        return ResponseEntity.ok(id);
    }
}
