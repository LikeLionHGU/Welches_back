package likelion.summer.welches.communityPost.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.user.domain.entity.User;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CommunityPost extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.ALL)
    private List<CommunityPostComment> communityPostComment;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.ALL)
    private List<CommunityPostLike> communityPostLikeList;


    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static CommunityPost toAdd(User user, Project project, String contents) {
        return CommunityPost.builder()
                .user(user)
                .project(project)
                .contents(contents)
                .build();
    }
}
