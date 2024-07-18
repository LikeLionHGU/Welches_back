package likelion.summer.welches.communityPost.domain.repository;

import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
}
