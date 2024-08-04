package likelion.summer.welches.bookMark.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.bookMark.application.service.BookMarkService;
import likelion.summer.welches.bookMark.presentation.request.BookMarkAddRequest;
import likelion.summer.welches.bookMark.presentation.request.BookMarkUpdateRequest;
import likelion.summer.welches.bookMark.presentation.response.BookMarkResponse;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.post.presentation.response.PostGetResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookMarkController {
    private final BookMarkService bookMarkService;
    private final JWTProvider jwtProvider;

    @PostMapping("/bookmark/add") // 갈피 생성 요청이 들어오면 자동으로 갈피 관리 권한을 부여해줌 or 프로젝트 관리자는 무조건 갈피 관리 권한이 있음
    public ResponseEntity<Long> addBookMark(HttpServletRequest request, @RequestBody BookMarkAddRequest bookMarkAddRequest) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(bookMarkService.addBookMark(userId, bookMarkAddRequest));
    }

    @GetMapping("/bookmark/get/{id}")
    public ResponseEntity<BookMarkResponse> getBookMarkSetting(@PathVariable Long id) {
        return ResponseEntity.ok(bookMarkService.getBookMark(id));
    }

    @PatchMapping("/bookmark/update/{id}")
    public ResponseEntity<Long> updateBookMark(@PathVariable Long id, @RequestBody BookMarkUpdateRequest request) {
        return ResponseEntity.ok(bookMarkService.updateBookMark(id, request));
    }


    @GetMapping("/bookmark/get/default/{id}")
    public ResponseEntity<PostGetResponse> getDefaultPost(@PathVariable Long id) { // id는 갈피 아이디
        return ResponseEntity.ok(bookMarkService.getDefaultPost(id));
    }

    @DeleteMapping("/bookmark/delete/{id}")
    public ResponseEntity<Long> deleteBookmark(HttpServletRequest request, @PathVariable Long id) { // id는 갈피 아이디
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(bookMarkService.deleteBookMark(id));
    }
}
