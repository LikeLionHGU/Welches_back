package likelion.summer.welches.projectCommentLike.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.user.domain.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ProjectCommentLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProjectComment projectComment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static ProjectCommentLike toAdd(User user, ProjectComment projectComment) {
        return ProjectCommentLike.builder()
                .user(user)
                .projectComment(projectComment)
                .build();
    }
}
