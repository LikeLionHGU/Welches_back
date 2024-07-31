package likelion.summer.welches.communityPost.domain.repository;

import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import likelion.summer.welches.communityPostCommentLike.domain.entity.CommunityPostCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {

    @Query("select r from CommunityPost r where r.project.id = :id")
    List<CommunityPost> findCommunityPostByProjectId(Long id);
}
