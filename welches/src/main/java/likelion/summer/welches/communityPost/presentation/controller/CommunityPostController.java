package likelion.summer.welches.communityPost.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.communityPost.application.service.CommunityPostService;
import likelion.summer.welches.communityPost.presentation.request.CommunityPostAddRequest;
import likelion.summer.welches.communityPost.presentation.response.CommunityPostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityPostController {
    private final CommunityPostService communityPostService;
    private final JWTProvider jwtProvider;

    @PostMapping("/post/community/upload")
    public ResponseEntity<Long> addCommunityPost(HttpServletRequest request, @RequestBody CommunityPostAddRequest communityPostAddRequest) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.addCommunityPost(userId, communityPostAddRequest.getProjectId(), communityPostAddRequest.getContents(), communityPostAddRequest.getTitle()));
    }

    @DeleteMapping("/post/community/delete/{id}")
    public ResponseEntity<Long> deleteCommunityPost(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.deleteCommunityPost(userId, id));
    }

    @GetMapping("/post/community/get/{id}")
    public ResponseEntity<List<CommunityPostResponse>> getAllCommunityPostList(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(communityPostService.getCommunityPost(userId, id));
    }
}
