package likelion.summer.welches.communityPostComment.application.service;

import likelion.summer.welches.communityPost.domain.repository.CommunityPostRepository;
import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import likelion.summer.welches.communityPostComment.domain.repository.CommunityPostCommentRepository;
import likelion.summer.welches.communityPostCommentLike.domain.entity.CommunityPostCommentLike;
import likelion.summer.welches.communityPostCommentLike.domain.repository.CommunityPostCommentLikeRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityPostCommentService {
    private final CommunityPostCommentRepository communityPostCommentRepository;
    private final UserRepository userRepository;
    private final CommunityPostRepository communityPostRepository;
    private final CommunityPostCommentLikeRepository communityPostCommentLikeRepository;

    @Transactional
    public Long addComment(String userId, Long postId, String contents) {
        return communityPostCommentRepository.save(CommunityPostComment.toAdd(userRepository.findUserByUserId(userId), communityPostRepository.findById(postId).orElse(null), contents)).getId();
    }

    @Transactional
    public Long deleteComment(String userId, Long postCommentId) {
        CommunityPostComment communityPostComment = communityPostCommentRepository.findById(postCommentId).orElse(null);
        if(communityPostComment != null) {
            List<CommunityPostCommentLike> communityPostCommentLikeList = communityPostComment.getCommunityPostCommentLikeList();
            communityPostCommentLikeRepository.deleteAll(communityPostCommentLikeList); // 관련 댓글 좋아요 모두 삭제

            if(communityPostComment.getUser().getId().equals(userId)) {
                communityPostCommentRepository.delete(communityPostComment);
            }
        }


        return postCommentId;
    }
}
