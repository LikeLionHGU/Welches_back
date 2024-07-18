package likelion.summer.welches.projectLike.domain.repository;

import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {

    @Query("select r from ProjectLike r where r.user.id = :userId and r.project.id = :projectId")
    ProjectLike findProjectLikeByUserIdAndProjectId(String userId, Long projectId);
}
