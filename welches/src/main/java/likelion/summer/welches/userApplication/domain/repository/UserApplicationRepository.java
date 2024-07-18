package likelion.summer.welches.userApplication.domain.repository;

import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserApplicationRepository extends JpaRepository<UserApplication, Long> {
    @Query("select r from UserApplication r where r.user.id = :userId and r.project.id = :projectId")
    UserApplication findUserApplicationByUserIdAndProjectId(String userId, Long projectId);
}
