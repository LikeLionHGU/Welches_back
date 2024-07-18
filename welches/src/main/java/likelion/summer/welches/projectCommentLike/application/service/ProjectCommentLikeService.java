package likelion.summer.welches.projectCommentLike.application.service;

import likelion.summer.welches.projectComment.domain.repository.ProjectCommentRepository;
import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import likelion.summer.welches.projectCommentLike.domain.repository.ProjectCommentLikeRepository;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectCommentLikeService {
    private final ProjectCommentLikeRepository projectCommentLikeRepository;
    private final UserRepository userRepository;
    private final ProjectCommentRepository projectCommentRepository;

    @Transactional
    public void changeProjectCommentLike(String userId, Long projectCommentId) {
        ProjectCommentLike projectCommentLike = projectCommentLikeRepository.findProjectLikeByUserIdAndProjectId(userId, projectCommentId);

        if(projectCommentLike == null) {
            projectCommentLikeRepository.save(ProjectCommentLike.toAdd(userRepository.findUserByUserId(userId), projectCommentRepository.findById(projectCommentId).orElse(null)));
        } else {
            projectCommentLikeRepository.delete(projectCommentLike);
        }
    }
}
