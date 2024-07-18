package likelion.summer.welches.project.application.service;

import likelion.summer.welches.commons.config.ImageUploader;
import likelion.summer.welches.project.application.dto.ProjectAddDto;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.project.presentation.response.ProjectResponse;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ImageUploader imageUploader;
    private final UserRepository userRepository;

    @Transactional
    public Long addProject(ProjectAddDto dto) {
        String imageUrl = imageUploader.toUpload(dto.getFile());
        User user = userRepository.findUserByUserId(dto.getUserId());

        Project project = projectRepository.save(Project.toAdd(dto, imageUrl, user));
        return project.getId();
    }

    @Transactional
    public List<ProjectResponse> getAllList(String userId) {
        List<Project> projectList = projectRepository.findAll();
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }

        return projectResponseList;
    }

    @Transactional
    public List<ProjectResponse> getFinishedList(String userId) {
        List<Project> projectList = projectRepository.findProjectsByIsFinished(true);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }

        return projectResponseList;
    }

    @Transactional
    public List<ProjectResponse> getProjectListWithCategory(String userId, String category) {
        List<Project> projectList = projectRepository.findProjectsByCategoryEquals(category);
        List<ProjectResponse> projectResponseList = projectList.stream().map(ProjectResponse::toResponse).toList();

        for(ProjectResponse p : projectResponseList) {
            p.setIsOwner(p.getOwnerId().equals(userId)); // 현재 접속자가 해당 프로젝트의 owner일 경우에는 true를 넣어서 return
        }
        return projectResponseList;
    }

//    @Transactional
//    public ProjectResponse getProject(String userId, Long projectId) {
//        Project project = projectRepository.findById(projectId).orElse(null);
//        ProjectResponse projectResponse
//    }
}
