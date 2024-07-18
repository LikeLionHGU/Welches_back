package likelion.summer.welches.userProject.application.service;

import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import likelion.summer.welches.userProject.domain.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProjectService {
    private final UserProjectRepository userProjectRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long addProjectUser(String userId, Long projectId) {
        return userProjectRepository.save(UserProject.toAdd(userRepository.findUserByUserId(userId), projectRepository.findById(projectId).orElse(null))).getId();
    }

    @Transactional
    public Long deleteProjectUser(String userId, Long projectId) {
        UserProject userProject = userProjectRepository.findUserProjectByUserIdAndProjectId(userId, projectId);
        Long userProjectId = userProject.getId();

        userProjectRepository.delete(userProject);
        return userProjectId;
    }
}
