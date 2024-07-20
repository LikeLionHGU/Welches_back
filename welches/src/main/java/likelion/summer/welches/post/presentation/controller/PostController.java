package likelion.summer.welches.post.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.post.application.service.PostService;
import likelion.summer.welches.post.presentation.request.PostAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JWTProvider jwtProvider;

    @PostMapping("/post/upload")
    public ResponseEntity<Long> toAddPost(HttpServletRequest request, @RequestBody PostAddRequest postAddRequest) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(postService.addPost(userId, postAddRequest.getContents(), postAddRequest.getBookMarkId()));
    }
}
