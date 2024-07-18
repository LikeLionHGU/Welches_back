package likelion.summer.welches.userApplication.application.service;

import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userApplication.domain.repository.UserApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserApplicationService {
    private final UserApplicationRepository userApplicationRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long addRequest(String userId, Long projectId) {
        return userApplicationRepository.save(UserApplication.toAdd(userRepository.findUserByUserId(userId), projectRepository.findById(projectId).orElse(null))).getId();
    }

    @Transactional
    public Long deleteRequest(String userId, Long projectId) {
        UserApplication userApplication = userApplicationRepository.findUserApplicationByUserIdAndProjectId(userId, projectId);
        Long id = userApplication.getId();
        userApplicationRepository.delete(userApplication);

        return id;
    }
}
