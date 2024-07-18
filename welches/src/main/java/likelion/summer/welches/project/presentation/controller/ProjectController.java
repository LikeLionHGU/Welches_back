package likelion.summer.welches.project.presentation.controller;


import jakarta.servlet.http.HttpServletRequest;
import likelion.summer.welches.commons.jwt.JWTProvider;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.application.service.ProjectService;
import likelion.summer.welches.project.presentation.response.ProjectAddRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
}
