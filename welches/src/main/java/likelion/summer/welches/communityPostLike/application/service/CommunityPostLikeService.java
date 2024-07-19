package likelion.summer.welches.communityPostLike.application.service;

import likelion.summer.welches.communityPost.domain.repository.CommunityPostRepository;
import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import likelion.summer.welches.communityPostLike.domain.repository.CommunityPostLikeRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityPostLikeService {
    private final CommunityPostLikeRepository communityPostLikeRepository;
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;

    @Transactional
    public Long changeLike(String userId, Long communityPostId) {
        CommunityPostLike communityPostLike = communityPostLikeRepository.findCommunityPostLikeByUserIdAndPostId(userId, communityPostId);
        Long response;

        if(communityPostLike != null) {
            response = communityPostLike.getId();
            communityPostLikeRepository.delete(communityPostLike);
        } else {
            communityPostLike = communityPostLikeRepository.save(CommunityPostLike.toAdd(userRepository.findUserByUserId(userId), communityPostRepository.findById(communityPostId).orElse(null)));
            response = communityPostLike.getId();
        }

        return response;
    }
}
