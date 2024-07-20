package likelion.summer.welches.post.domain.entity;

import jakarta.persistence.*;
import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.commons.entity.BaseEntity;
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
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookMark bookMark;

    private String contents;

    private Boolean isConfirmed;

    private Boolean isAllowed;

    private String rejectedReason;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static Post toAdd(User user, BookMark bookMark, String contents, Boolean isAllowed) { // 처음에 생성하는 부분
        return Post.builder()
                .user(user)
                .bookMark(bookMark)
                .contents(contents)
                .isConfirmed(false)
                .isAllowed(isAllowed)
                .build();
    }
}
