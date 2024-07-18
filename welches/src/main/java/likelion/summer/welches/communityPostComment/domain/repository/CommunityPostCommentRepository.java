package likelion.summer.welches.communityPostComment.domain.repository;

import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostCommentRepository extends JpaRepository<CommunityPostComment, Long> {
}
