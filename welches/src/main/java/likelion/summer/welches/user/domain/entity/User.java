package likelion.summer.welches.user.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.commons.security.Authority;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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


    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(o -> o.setUser(this));

    }
}
