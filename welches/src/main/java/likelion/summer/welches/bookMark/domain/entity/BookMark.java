package likelion.summer.welches.bookMark.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.commons.entity.BaseEntity;
import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.temporaryPost.domain.entity.TemporaryPost;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
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
public class BookMark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isShared;

    private Boolean isSameTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;

    @OneToMany(mappedBy = "bookMark", cascade = CascadeType.ALL)
    private List<UserBookMark> userBookMarkList;

    @OneToMany(mappedBy = "bookMark", cascade = CascadeType.ALL)
    private List<TemporaryPost> temporaryPostList;

    @OneToMany(mappedBy = "bookMark", cascade = CascadeType.ALL)
    private List<Post> postList;


    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static BookMark toAdd(Project project) {
        return BookMark.builder()
                .project(project)
                .build();
    }
}
