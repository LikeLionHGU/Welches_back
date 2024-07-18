package likelion.summer.welches.project.presentation.controller;


import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.application.service.ProjectService;
import likelion.summer.welches.project.presentation.request.ProjectAddRequest;
import likelion.summer.welches.project.presentation.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final JWTProvider jwtProvider;

    @PostMapping(value = "/project/add", consumes = "multipart/form-data")
    public ResponseEntity<Long> addProject(@RequestPart("post") ProjectAddRequest projectAddRequest, @RequestPart("file") MultipartFile file, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        Long projectId = projectService.addProject(ProjectAddDto.toAdd(projectAddRequest, file, userId));

        return ResponseEntity.ok(projectId);
    }

    @GetMapping("/project/get/all")
    public ResponseEntity<List<ProjectResponse>> getAllProjectList(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(projectService.getAllList(userId));
    }

//    @GetMapping("/project/get/recurit")
//    public ResponseEntity<List<ProjectResponse>> getRecruitProjectList(HttpServletRequest request) {
//        String token = jwtProvider.resolveToken(request);
//        String userId = jwtProvider.getAccount(token);
//    }

    @GetMapping("/project/get/finished")
    public ResponseEntity<List<ProjectResponse>> getAllFinishedProjectList(HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(projectService.getFinishedList(userId));
    }

    @GetMapping("/project/get/{category}")
    public ResponseEntity<List<ProjectResponse>> getProjectListWithCategory(@PathVariable String category, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(projectService.getProjectListWithCategory(userId, category));
    }

//    @GetMapping("/project/get/{id}")
//    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id, HttpServletRequest request) {
//        String token = jwtProvider.resolveToken(request);
//        String userId = jwtProvider.getAccount(token);
//    }

}
