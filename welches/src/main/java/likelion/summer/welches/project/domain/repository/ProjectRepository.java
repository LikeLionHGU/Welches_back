package likelion.summer.welches.project.domain.repository;

import likelion.summer.welches.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//    @Query("select r from Project r where r.type = :type")
//    List<Post> findPostListByType(@Param("type") int type);
}
