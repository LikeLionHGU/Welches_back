package likelion.summer.welches.projectCommentLike.domain.repository;

import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectCommentLikeRepository extends JpaRepository<ProjectCommentLike, Long> {

    @Query("select r from ProjectCommentLike r where r.user.id = :userId and r.projectComment.id = :projectCommentId")
    ProjectCommentLike findProjectLikeByUserIdAndProjectId(String userId, Long projectCommentId);
}
