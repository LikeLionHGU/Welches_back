package likelion.summer.welches.project.presentation.controller;


import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.application.service.ProjectService;
import likelion.summer.welches.project.presentation.request.ProjectAddRequest;
import likelion.summer.welches.project.presentation.request.ProjectCategoryRequest;
import likelion.summer.welches.project.presentation.request.ProjectUpdateRequest;
import likelion.summer.welches.project.presentation.response.ProjectGetResponse;
import likelion.summer.welches.project.presentation.response.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProjectController {
    private final ProjectService projectService;
    private final JWTProvider jwtProvider;

    @PostMapping(value = "/project/add", consumes = "multipart/form-data")
    public ResponseEntity<Long> addProject(@RequestPart("post") ProjectAddRequest projectAddRequest, @RequestPart(value = "file", required = false) MultipartFile file, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        Long projectId = projectService.addProject(ProjectAddDto.toAdd(projectAddRequest, file, userId));

        return ResponseEntity.ok(projectId);
    }

    @GetMapping("/project/get/all") // 모든 프로젝트 확인
    public ResponseEntity<List<ProjectResponse>> getAllProjectList(HttpServletRequest request) {

        String token = jwtProvider.resolveToken(request);
        System.out.println(token);
        String userId = null;
        if(!token.equals("Bearer null")) {
            userId = jwtProvider.getAccount(token);
        }

        List<ProjectResponse> list = projectService.getAllList(userId);
        Collections.reverse(list);
        return ResponseEntity.ok(list);

    }

    @GetMapping("/project/get/{id}")
    public ResponseEntity<ProjectGetResponse> getOneProject(@PathVariable Long id, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(projectService.getProjectInformation(id, userId));
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

        List<ProjectResponse> list = projectService.getFinishedList(userId);
        Collections.reverse(list);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/project/get/category/finish/{category}/{isFinished}") // 대분류 사용하지 않고 검색하는 api
    public ResponseEntity<List<ProjectResponse>> getProjectListWithCategoryAndFinish(HttpServletRequest request, @PathVariable String category, @PathVariable Boolean isFinished) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        List<ProjectResponse> list = projectService.getProjectListWithCategoryAndFinish(userId, category, isFinished);
        Collections.reverse(list);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/project/get/category/recruit/{category}/{isRecruit}") // 대분류 사용하지 않고 검색하는 api
    public ResponseEntity<List<ProjectResponse>> getProjectListWithCategoryAndRecruit(HttpServletRequest request, @PathVariable String category, @PathVariable Boolean isRecruit) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        List<ProjectResponse> list = projectService.getProjectListWithCategoryAndRecruit(userId, category, isRecruit);
        Collections.reverse(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/project/get/category/big/finish/{category}/{isFinished}") // 대분류를 사용해서 검색하는 api
    public ResponseEntity<List<ProjectResponse>> getProjectListWithBigCategoryAndFinish(HttpServletRequest request, @PathVariable String category, @PathVariable Boolean isFinished) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);
        List<ProjectResponse> list = projectService.getProjectListWithBigCategoryAndFinish(userId, category, isFinished);

        Collections.reverse(list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/project/get/category/big/recruit/{category}/{isRecruit}") // 대분류를 사용해서 검색하는 api
    public ResponseEntity<List<ProjectResponse>> getProjectListWithBigCategoryAndRecruit(HttpServletRequest request, @PathVariable String category, @PathVariable Boolean isRecruit) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);
        List<ProjectResponse> list = projectService.getProjectListWithBigCategoryAndRecruit(userId, category, isRecruit);

        Collections.reverse(list);
        return ResponseEntity.ok(list);
    }

    @PatchMapping("/project/update/owner/{userId}/{projectId}")
    public ResponseEntity<Long> updateProjectOwner(@PathVariable String userId, @PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.updateProjectOwner(userId, projectId));
    }

//    @GetMapping("/project/get/{id}")
//    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id, HttpServletRequest request) {
//        String token = jwtProvider.resolveToken(request);
//        String userId = jwtProvider.getAccount(token);
//    }

    @GetMapping("/project/get/best")
    public ResponseEntity<List<ProjectResponse>> getBestProject() {

        List<ProjectResponse> list = projectService.getBestProjectList();
        Collections.reverse(list);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/project/delete/{id}")
    public ResponseEntity<Long> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }

    @GetMapping("/project/get/name/{name}")
    public ResponseEntity<List<ProjectResponse>> getProjectsWithName(@PathVariable String name, HttpServletRequest request) {
        String token = jwtProvider.resolveToken(request);
        String userId = jwtProvider.getAccount(token);

        return ResponseEntity.ok(projectService.findProjectsWithName(name, userId));
    }

    @PatchMapping(value = "/project/update", consumes = "multipart/form-data")
    public ResponseEntity<Long> updateProject(@RequestPart("post") ProjectUpdateRequest projectAddRequest, @RequestPart(value = "file", required = false) MultipartFile file) {
        return ResponseEntity.ok(projectService.updateProject(projectAddRequest, file));
    }
}
