package likelion.summer.welches.temporaryPost.application.dto;

import likelion.summer.welches.bookMark.application.dto.BookMarkTemporaryPostDto;
import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.temporaryPost.domain.entity.TemporaryPost;
import likelion.summer.welches.user.application.dto.UserTemporaryPostDto;
import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemporaryPostDto { // 반환에 사용
    private Long id;

    private String contents;
    private UserTemporaryPostDto user;
    private BookMarkTemporaryPostDto bookMark;

    public static TemporaryPostDto toGet(TemporaryPost temporaryPost) {
        return TemporaryPostDto.builder()
                .id(temporaryPost.getId())
                .contents(temporaryPost.getContents())
                .user(UserTemporaryPostDto.toReturn(temporaryPost.getUser()))
                .bookMark(BookMarkTemporaryPostDto.toReturn(temporaryPost.getBookMark()))
                .build();
    }
}
