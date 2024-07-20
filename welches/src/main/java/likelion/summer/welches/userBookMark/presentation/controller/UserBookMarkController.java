package likelion.summer.welches.userBookMark.presentation.controller;

import likelion.summer.welches.userBookMark.application.service.UserBookMarkService;
import likelion.summer.welches.userBookMark.presentation.request.UserBookMarkAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserBookMarkController {
    private final UserBookMarkService userBookMarkService;

    @PostMapping("/user/bookmark/add")
    public ResponseEntity<Long> addUserBookMark(UserBookMarkAddRequest request) {
        return ResponseEntity.ok(userBookMarkService.addUserBookMark(request.getUserId(), request.getBookMarkId()));
    }
}
