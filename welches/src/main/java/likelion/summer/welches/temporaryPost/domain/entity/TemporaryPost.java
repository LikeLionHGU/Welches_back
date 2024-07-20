package likelion.summer.welches.temporaryPost.domain.entity;

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
public class TemporaryPost extends BaseEntity { // 어느 갈피에 어떤 사용자가 임시 저장 했는지에 대한 기능
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private BookMark bookMark;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public static TemporaryPost toAdd(User user, BookMark bookMark, String contents) {
        return TemporaryPost.builder()
                .user(user)
                .bookMark(bookMark)
                .contents(contents)
                .build();
    }
}
