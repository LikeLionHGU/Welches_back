package likelion.summer.welches.projectLike.application.service;

import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.projectLike.domain.repository.ProjectLikeRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectLikeService {
    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Transactional
    public void translateLikeInformation(String userId, Long projectId) {
        ProjectLike projectLike = projectLikeRepository.findProjectLikeByUserIdAndProjectId(userId, projectId);

        if(projectLike == null) {
            projectLikeRepository.save(ProjectLike.toAdd(userRepository.findUserByUserId(userId), projectRepository.findById(projectId).orElse(null)));
        } else {
            projectLikeRepository.delete(projectLike);
        }
    }
}
