package likelion.summer.welches.projectComment.application.service;

import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.projectComment.domain.repository.ProjectCommentRepository;
import likelion.summer.welches.projectCommentLike.application.service.ProjectCommentLikeService;
import likelion.summer.welches.projectCommentLike.domain.repository.ProjectCommentLikeRepository;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectCommentService {
    private final ProjectCommentRepository projectCommentRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final ProjectCommentLikeService projectCommentLikeService;

    @Transactional
    public Long addProjectComment(String userId, Long projectId, String contents) {
        User user = userRepository.findById(userId).orElse(null);
        Project project = projectRepository.findById(projectId).orElse(null);


        ProjectComment projectComment = projectCommentRepository.save(ProjectComment.toAdd(user, project, contents));
        return projectComment.getId();
    }

    @Transactional
    public void deleteProjectComment(Long projectCommentId) {
        ProjectComment projectComment = projectCommentRepository.findById(projectCommentId).orElse(null);

        if(projectComment != null) {
            System.out.println("?!");
            projectCommentLikeService.removeAllLikes(projectCommentId); // 좋아요도 모두 삭제
            projectCommentRepository.delete(projectComment);
        }
    }
}
