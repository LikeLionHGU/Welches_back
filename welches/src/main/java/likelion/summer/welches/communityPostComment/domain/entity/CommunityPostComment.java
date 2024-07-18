package likelion.summer.welches.communityPostComment.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
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
public class CommunityPostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private CommunityPost communityPost;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static CommunityPostComment toAdd(User user, CommunityPost communityPost, String contents) {
        return CommunityPostComment.builder()
                .user(user)
                .communityPost(communityPost)
                .contents(contents)
                .build();
    }
}
