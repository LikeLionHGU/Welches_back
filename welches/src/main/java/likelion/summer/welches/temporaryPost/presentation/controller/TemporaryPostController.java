package likelion.summer.welches.temporaryPost.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.temporaryPost.application.dto.TemporaryPostDto;
import likelion.summer.welches.temporaryPost.application.service.TemporaryPostService;
import likelion.summer.welches.temporaryPost.presentation.request.TemporaryPostAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TemporaryPostController {
    private final TemporaryPostService temporaryPostService;
    private final JWTProvider jwtProvider;

    // 임시저장 글이 있으면 삭제 후 다시 저장
    // 임시저장 글이 없으면 그냥 저장
    // 발행 검사를 진행할 시에는 임시 저장 글 삭제
    @PostMapping("/post/temp/upload")
    public ResponseEntity<Long> uploadTemporaryPost(HttpServletRequest request, @RequestBody TemporaryPostAddRequest postAddRequest) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(temporaryPostService.upload(userId, postAddRequest.getContents(), postAddRequest.getBookMarkId()));
    }

    @GetMapping("/post/temp/get/{id}") // 갈피 id
    public ResponseEntity<TemporaryPostDto> getTemporaryPost(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);
        TemporaryPostDto temporaryPostDto = temporaryPostService.getTemporaryPost(userId, id);

        if(temporaryPostDto != null) {
            return ResponseEntity.ok(temporaryPostDto);
        } else {
            return ResponseEntity.ok(null);
        }
    }
}
