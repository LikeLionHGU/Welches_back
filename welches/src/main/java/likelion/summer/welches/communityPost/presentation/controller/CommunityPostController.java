package likelion.summer.welches.communityPost.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.communityPost.application.service.CommunityPostService;
import likelion.summer.welches.communityPost.presentation.request.CommunityPostAddRequest;
import likelion.summer.welches.communityPost.presentation.response.CommunityPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityPostController {
    private final CommunityPostService communityPostService;
    private final JWTProvider jwtProvider;

    @PostMapping(value = "/post/community/upload", consumes = "multipart/form-data")
    public ResponseEntity<Long> addCommunityPost(HttpServletRequest request, @RequestPart("post") CommunityPostAddRequest communityPostAddRequest, @RequestPart(value = "file", required = false) MultipartFile file) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.addCommunityPost(userId, communityPostAddRequest.getProjectId(), communityPostAddRequest.getContents(), communityPostAddRequest.getTitle(), file));
    }

    @DeleteMapping("/post/community/delete/{id}")
    public ResponseEntity<Long> deleteCommunityPost(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.deleteCommunityPost(id));
    }

    @GetMapping("/post/community/get/{id}")
    public ResponseEntity<List<CommunityPostResponse>> getAllCommunityPostList(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.getCommunityPost(userId, id));
    }

    @GetMapping("/post/community/get/one/{id}")
    public ResponseEntity<CommunityPostResponse> getCommunityPost(HttpServletRequest request, @PathVariable Long id) { // communityPostId
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.getPost(userId, id));
    }
}
