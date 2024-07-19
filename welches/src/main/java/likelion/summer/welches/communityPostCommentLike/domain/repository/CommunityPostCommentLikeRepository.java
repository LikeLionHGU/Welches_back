package likelion.summer.welches.communityPostCommentLike.domain.repository;

import likelion.summer.welches.communityPostCommentLike.domain.entity.CommunityPostCommentLike;
import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityPostCommentLikeRepository extends JpaRepository<CommunityPostCommentLike, Long> {
    @Query("select r from CommunityPostCommentLike r where r.user.id = :userId and r.communityPostComment.id = :communityPostCommentId")
    CommunityPostCommentLike findCommunityPostCommentLikeByUserIdAndCommunityPostCommentId(String userId, Long communityPostCommentId);
}
