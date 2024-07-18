package likelion.summer.welches.communityPost.application.service;

import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import likelion.summer.welches.communityPost.domain.repository.CommunityPostRepository;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityPostService {
    private final CommunityPostRepository communityPostRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public Long addCommunityPost(String userId, Long projectId, String contents) {
        return communityPostRepository.save(CommunityPost.toAdd(userRepository.findUserByUserId(userId), projectRepository.findById(projectId).orElse(null), contents)).getId();
    }
}
