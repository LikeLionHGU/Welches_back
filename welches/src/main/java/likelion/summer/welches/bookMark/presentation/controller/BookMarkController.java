package likelion.summer.welches.bookMark.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.bookMark.application.service.BookMarkService;
import likelion.summer.welches.bookMark.presentation.response.BookMarkResponse;
import likelion.summer.welches.commons.jwt.JWTProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookMarkController {
    private final BookMarkService bookMarkService;
    private final JWTProvider jwtProvider;

    @PostMapping("/bookmark/add/{id}") // 갈피 생성 요청이 들어오면 자동으로 갈피 관리 권한을 부여해줌 or 프로젝트 관리자는 무조건 갈피 관리 권한이 있음
    public ResponseEntity<Long> addBookMark(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(bookMarkService.addBookMark(userId, id));
    }

    @GetMapping("/bookmark/get/{id}")
    public ResponseEntity<BookMarkResponse> getBookMarkSetting(@PathVariable Long id) {
        return ResponseEntity.ok(bookMarkService.getBookMark(id));
    }
}
