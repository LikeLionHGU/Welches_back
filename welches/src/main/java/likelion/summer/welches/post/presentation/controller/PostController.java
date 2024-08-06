package likelion.summer.welches.post.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.post.application.service.PostService;
import likelion.summer.welches.post.presentation.request.PostAddRequest;
import likelion.summer.welches.post.presentation.request.PostConfirmRequest;
import likelion.summer.welches.post.presentation.response.PostBookMarkResponse;
import likelion.summer.welches.post.presentation.response.PostGetAllResponse;
import likelion.summer.welches.post.presentation.response.PostGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PatchMapping("/post/confirm")
    public ResponseEntity<Long> confirmPost(@RequestBody PostConfirmRequest request) {
        return ResponseEntity.ok(postService.confirmPost(request.getId(), request.getIsAllowed(), request.getRejectedReason(), request.getContents()));
    }

    @GetMapping("/post/get/{id}") // id는 postId
    public ResponseEntity<PostGetResponse> getSelectedPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getSelectedPost(id));
    }

    @GetMapping("/post/get/all/{id}") // 갈피 아이디
    public ResponseEntity<List<PostGetAllResponse>> getAllPost(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(postService.getAllResponseList(userId, id));
    }

    @GetMapping("/post/get/wait/{id}") // 검토 대기 post
    public ResponseEntity<List<PostGetAllResponse>> getAllWaitPost(@PathVariable Long id) { // 갈피 아이디
        return ResponseEntity.ok(postService.getAllWaitPosts(id));
    }

    @GetMapping("/post/get/approve/{id}") // 승인된 post
    public ResponseEntity<List<PostGetAllResponse>> getAllApprovePost(@PathVariable Long id) { // 갈피 아이디
        return ResponseEntity.ok(postService.getAllApprovedPosts(id));
    }

    @GetMapping("/post/get/disapprove/{id}") // 미승인 post
    public ResponseEntity<List<PostGetAllResponse>> getAllDisapprovePost(@PathVariable Long id) { // 갈피 아이디
        return ResponseEntity.ok(postService.getAllDisapprovedPosts(id));
    }

    @GetMapping("/post/get/confirmed/all/{id}") // 검토 된 post
    public ResponseEntity<List<PostGetAllResponse>> getAllConfirmedPost(@PathVariable Long id) { // 갈피 아이디
        return ResponseEntity.ok(postService.getAllConfirmedPosts(id));
    }

    @GetMapping("/post/get/bookmark/{id}") // 현재 갈피의 최신 버전 가져오기
    public ResponseEntity<PostBookMarkResponse> getCurrentPost(@PathVariable Long id) {
        return ResponseEntity.ok(postService.getCurrentPost(id));
    }


}
