package likelion.summer.welches.userApplication.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.userApplication.application.service.UserApplicationService;
import likelion.summer.welches.userApplication.presentation.request.UserApplicationApproveRequest;
import likelion.summer.welches.userApplication.presentation.request.UserApplicationDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApplicationController {
    private final UserApplicationService userApplicationService;
    private final JWTProvider jwtProvider;

    @PostMapping("/project/application/request/{id}")
    public ResponseEntity<Long> addUserRequest(HttpServletRequest request, @PathVariable Long id) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);
        return ResponseEntity.ok(userApplicationService.addRequest(userId, id));
    }

    @DeleteMapping("/project/application/delete/{userId}/{projectId}")
    public ResponseEntity<Long> deleteUserRequest(@PathVariable String userId, @PathVariable Long projectId) {
        return ResponseEntity.ok(userApplicationService.deleteRequest(userId, projectId));
    }

    @PostMapping("/project/application/approve")
    public ResponseEntity<Long> approveUserRequest(@RequestBody UserApplicationApproveRequest request) {
        Long response = userApplicationService.approveUserRequest(request.getUserId(), request.getProjectId());
        userApplicationService.deleteRequest(request.getUserId(), request.getProjectId());
        return ResponseEntity.ok(response);
    }
}
