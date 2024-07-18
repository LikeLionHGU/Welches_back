package likelion.summer.welches.projectCommentLike.domain.repository;

import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectCommentLikeRepository extends JpaRepository<ProjectCommentLike, Long> {

    @Query("select r from ProjectCommentLike r where r.user.id = :userId and r.projectComment.id = :projectCommentId")
    ProjectCommentLike findProjectCommentLikeByUserIdAndProjectCommentId(String userId, Long projectCommentId);

    @Query("select r from ProjectCommentLike r where r.projectComment.id = :projectCommentId")
    List<ProjectCommentLike> findProjectCommentLikesByProjectCommentId(Long projectCommentId);
}
