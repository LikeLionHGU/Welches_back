package likelion.summer.welches.user.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.commons.security.Authority;
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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private List<Authority> roles = new ArrayList<>();

    public void setRoles(List<Authority> roles) {
        this.roles = roles;
        roles.forEach(o -> o.setUser(this));

    }
}
