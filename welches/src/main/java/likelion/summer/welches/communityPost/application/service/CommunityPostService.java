package likelion.summer.welches.communityPost.application.service;

import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import likelion.summer.welches.communityPost.domain.repository.CommunityPostRepository;
import likelion.summer.welches.communityPostComment.application.service.CommunityPostCommentService;
import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import likelion.summer.welches.communityPostLike.domain.repository.CommunityPostLikeRepository;
import likelion.summer.welches.project.domain.repository.ProjectRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityPostService {
    private final CommunityPostRepository communityPostRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final CommunityPostCommentService communityPostCommentService;
    private final CommunityPostLikeRepository communityPostLikeRepository;

    @Transactional
    public Long addCommunityPost(String userId, Long projectId, String contents) {
        return communityPostRepository.save(CommunityPost.toAdd(userRepository.findUserByUserId(userId), projectRepository.findById(projectId).orElse(null), contents)).getId();
    }

    @Transactional
    public Long deleteCommunityPost(String userId, Long postId) {
        CommunityPost communityPost = communityPostRepository.findById(postId).orElse(null);

        if(communityPost != null) {
            List<CommunityPostComment> communityPostCommentList = communityPost.getCommunityPostComment();
            for(CommunityPostComment p : communityPostCommentList) {
                communityPostCommentService.deleteComment(userId, p.getId());
            }
            List<CommunityPostLike> communityPostLikeList = communityPost.getCommunityPostLikeList();
            communityPostLikeRepository.deleteAll(communityPostLikeList);

            communityPostRepository.delete(communityPost);
        }

        return postId;
    }
}
