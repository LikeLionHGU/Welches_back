package likelion.summer.welches.communityPostLike.domain.repository;

import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommunityPostLikeRepository extends JpaRepository<CommunityPostLike, Long> {
    @Query("select r from CommunityPostLike r where r.user.id = :userId and r.communityPost.id = :communityPostId")
    CommunityPostLike findCommunityPostLikeByUserIdAndPostId(String userId, Long communityPostId);
}
