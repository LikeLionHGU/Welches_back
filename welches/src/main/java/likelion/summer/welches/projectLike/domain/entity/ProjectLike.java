package likelion.summer.welches.projectLike.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public static ProjectLike toAdd(User user, Project project) {
        return ProjectLike.builder()
                .user(user)
                .project(project)
                .build();
    }

}
