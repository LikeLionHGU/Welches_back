package likelion.summer.welches.userProject.domain.repository;

import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
    @Query("select r from UserProject r where r.user.id = :userId and r.project.id = :projectId")
    UserProject findUserProjectByUserIdAndProjectId(String userId, Long projectId);
}
