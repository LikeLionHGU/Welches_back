package likelion.summer.welches.communityPostCommentLike.application.service;

import likelion.summer.welches.communityPostComment.domain.repository.CommunityPostCommentRepository;
import likelion.summer.welches.communityPostCommentLike.domain.entity.CommunityPostCommentLike;
import likelion.summer.welches.communityPostCommentLike.domain.repository.CommunityPostCommentLikeRepository;
import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityPostCommentLikeService {
    private final CommunityPostCommentLikeRepository communityPostCommentLikeRepository;
    private final UserRepository userRepository;
    private final CommunityPostCommentRepository communityPostCommentRepository;

    @Transactional
    public Long changeLike(String userId, Long communityPostId) {
        CommunityPostCommentLike communityPostCommentLike = communityPostCommentLikeRepository.findCommunityPostCommentLikeByUserIdAndCommunityPostCommentId(userId, communityPostId);
        Long response;

        if(communityPostCommentLike != null) {
            response = communityPostCommentLike.getId();
            communityPostCommentLikeRepository.delete(communityPostCommentLike);
        } else {
            communityPostCommentLike = communityPostCommentLikeRepository.save(CommunityPostCommentLike.toAdd(userRepository.findUserByUserId(userId), communityPostCommentRepository.findById(communityPostId).orElse(null)));
            response = communityPostCommentLike.getId();
        }

        return response;
    }
}
