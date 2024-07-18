package likelion.summer.welches.projectCommentLike.application.service;

import likelion.summer.welches.projectComment.domain.repository.ProjectCommentRepository;
import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import likelion.summer.welches.projectCommentLike.domain.repository.ProjectCommentLikeRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectCommentLikeService {
    private final ProjectCommentLikeRepository projectCommentLikeRepository;
    private final UserRepository userRepository;
    private final ProjectCommentRepository projectCommentRepository;

    @Transactional
    public void changeProjectCommentLike(String userId, Long projectCommentId) {
        ProjectCommentLike projectCommentLike = projectCommentLikeRepository.findProjectCommentLikeByUserIdAndProjectCommentId(userId, projectCommentId);

        if(projectCommentLike == null) {
            projectCommentLikeRepository.save(ProjectCommentLike.toAdd(userRepository.findUserByUserId(userId), projectCommentRepository.findById(projectCommentId).orElse(null)));
        } else {
            projectCommentLikeRepository.delete(projectCommentLike);
        }
    }

    @Transactional
    public void removeAllLikes(Long projectCommentId) {
        List<ProjectCommentLike> projectCommentLikeList = projectCommentLikeRepository.findProjectCommentLikesByProjectCommentId(projectCommentId);
        projectCommentLikeRepository.deleteAll(projectCommentLikeList);
    }
}
