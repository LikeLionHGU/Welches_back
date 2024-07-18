package likelion.summer.welches.projectComment.domain.repository;

import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCommentRepository extends JpaRepository<ProjectComment, Long> {
}
