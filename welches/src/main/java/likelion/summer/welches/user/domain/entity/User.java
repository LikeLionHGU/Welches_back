package likelion.summer.welches.user.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.commons.security.Authority;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String information; // 자기소개 글

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Project> projectList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectComment> projectCommentList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectLike> projectLikeList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectCommentLike> projectCommentLikeList;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(o -> o.setUser(this));

    }
}
