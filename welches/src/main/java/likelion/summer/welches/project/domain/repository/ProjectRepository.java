package likelion.summer.welches.project.domain.repository;

import likelion.summer.welches.project.domain.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
