package likelion.summer.welches.projectComment.domain.entity;

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
@NoArgsConstructor
public class ProjectComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    public static ProjectComment toAdd(User user, Project project, String contents) {
        return ProjectComment.builder()
                .contents(contents)
                .user(user)
                .project(project)
                .build();
    }
}
