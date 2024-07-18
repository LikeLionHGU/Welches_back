package likelion.summer.welches.userApplication.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.project.domain.entity.Project;
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
public class UserApplication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static UserApplication toAdd(User user, Project project) {
        return UserApplication.builder()
                .user(user)
                .project(project)
                .build();
    }
}
