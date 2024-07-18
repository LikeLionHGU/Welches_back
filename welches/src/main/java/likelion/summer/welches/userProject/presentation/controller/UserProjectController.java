package likelion.summer.welches.userProject.presentation.controller;

import likelion.summer.welches.userProject.application.service.UserProjectService;
import likelion.summer.welches.userProject.presentation.request.UserProjectAddRequest;
import likelion.summer.welches.userProject.presentation.request.UserProjectDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserProjectController {
    private final UserProjectService userProjectService;

    @PostMapping("/project/user/add")
    public ResponseEntity<Long> addUser(@RequestBody UserProjectAddRequest request) {
        return ResponseEntity.ok(userProjectService.addProjectUser(request.getUserId(), request.getProjectId()));
    }

    @DeleteMapping("/project/user/delete")
    public ResponseEntity<Long> deleteUser(@RequestBody UserProjectDeleteRequest request) {
        return ResponseEntity.ok(userProjectService.deleteProjectUser(request.getUserId(), request.getProjectId()));
    }
}
