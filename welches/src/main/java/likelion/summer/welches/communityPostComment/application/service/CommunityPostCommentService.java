package likelion.summer.welches.communityPostComment.application.service;

import likelion.summer.welches.communityPost.domain.repository.CommunityPostRepository;
import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import likelion.summer.welches.communityPostComment.domain.repository.CommunityPostCommentRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityPostCommentService {
    private final CommunityPostCommentRepository communityPostCommentRepository;
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;

    @Transactional
    public Long addComment(String userId, Long postId, String contents) {
        return communityPostCommentRepository.save(CommunityPostComment.toAdd(userRepository.findUserByUserId(userId), communityPostRepository.findById(postId).orElse(null), contents)).getId();
    }
}
