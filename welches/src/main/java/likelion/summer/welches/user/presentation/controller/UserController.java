package likelion.summer.welches.user.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.config.ImageUploader;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.presentation.request.ProjectAddRequest;
import likelion.summer.welches.user.application.service.UserService;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.user.presentation.request.UserRequest;
import likelion.summer.welches.user.presentation.response.UserGetResponse;
import likelion.summer.welches.userProject.application.service.UserProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JWTProvider jwtProvider;


    @GetMapping("/user/info")
    public ResponseEntity<UserGetResponse> getUserInfo(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(userService.getUserInfo(userId));
    }

    @PostMapping(value = "/user/update", consumes = "multipart/form-data")
    public ResponseEntity<String> updateUser(HttpServletRequest request, @RequestPart(value = "post", required = false) UserRequest userRequest, @RequestPart(value = "profile", required = false) MultipartFile profile, @RequestPart(value = "back", required = false) MultipartFile back) {


        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);


        return ResponseEntity.ok(userService.updateUser(profile, back, userRequest, userId));
    }

    @GetMapping("/user/info/{id}")
    public ResponseEntity<UserGetResponse> getUserInfoWithId(HttpServletRequest request, @PathVariable String id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(userService.getUserInfoWithId(id, userId));
    }
}