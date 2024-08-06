package likelion.summer.welches.userProject.presentation.controller;

import likelion.summer.welches.userProject.application.service.UserProjectService;
import likelion.summer.welches.userProject.presentation.request.UserProjectAddRequest;
import likelion.summer.welches.userProject.presentation.request.UserProjectDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserProjectController {
    private final UserProjectService userProjectService;

    @PostMapping("/project/user/add")
    public ResponseEntity<Long> addUser(@RequestBody UserProjectAddRequest request) {
        return ResponseEntity.ok(userProjectService.addProjectUser(request.getUserId(), request.getProjectId()));
    }

    @DeleteMapping("/project/user/delete/{userId}/{projectId}")
    public ResponseEntity<Long> deleteUser(@PathVariable String userId, @PathVariable Long projectId) {
        return ResponseEntity.ok(userProjectService.deleteProjectUser(userId, projectId));
    }
}
