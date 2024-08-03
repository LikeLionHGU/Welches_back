package likelion.summer.welches.project.domain.repository;

import likelion.summer.welches.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
//    @Query("select r from Project r where r. = :type")
//    List<Project> findProjectsByCategoryAndIsFinishedAnd(@Param("type") int type);

    List<Project> findProjectsByIsFinished(Boolean isFinished);

    List<Project> findProjectsByBigCategoryEqualsAndIsFinished(String bigCategory, Boolean isFinished);
    List<Project> findProjectsByBigCategoryEqualsAndIsRecruit(String bigCategory, Boolean isRecruit);
    List<Project> findProjectsByCategoryEqualsAndIsFinished(String category, Boolean isFinished);
    List<Project> findProjectsByCategoryEqualsAndIsRecruit(String category, Boolean isRecruit);

}
