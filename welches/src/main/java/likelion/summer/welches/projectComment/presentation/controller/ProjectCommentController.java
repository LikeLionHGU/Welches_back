package likelion.summer.welches.projectComment.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.project.application.service.ProjectService;
import likelion.summer.welches.projectComment.application.service.ProjectCommentService;
import likelion.summer.welches.projectComment.presentation.request.ProjectCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProjectCommentController {
    private final ProjectCommentService projectCommentService;
    private final JWTProvider jwtProvider;

    @PostMapping("/project/comment/add/{id}")
    public ResponseEntity<Long> addProjectComment(HttpServletRequest request, @PathVariable Long id, @RequestBody ProjectCommentRequest projectCommentRequest) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(projectCommentService.addProjectComment(userId, id, projectCommentRequest.getContents()));
    }

    @DeleteMapping("/project/comment/delete/{id}")
    public ResponseEntity<Void> deleteProjectComment(HttpServletRequest request, @PathVariable Long id) {
        System.out.println("?!?!?!?!?!");
        projectCommentService.deleteProjectComment(id);

        return ResponseEntity.ok(null);
    }
}
