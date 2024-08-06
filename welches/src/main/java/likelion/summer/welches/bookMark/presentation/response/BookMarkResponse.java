package likelion.summer.welches.bookMark.presentation.response;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.user.application.dto.UserBookmarkDto;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookMarkResponse {
    private Long id;
    private String name;
    private Boolean isShared;
    private Boolean isSameTime;
    private List<UserBookmarkDto> userList;

    public static BookMarkResponse toResponse(BookMark bookMark) {
        return BookMarkResponse.builder()
                .id(bookMark.getId())
                .userList(bookMark.getUserBookMarkList().stream().map(UserBookmarkDto::toResponse).toList())
                .name(bookMark.getName())
                .isShared(bookMark.getIsShared())
                .isSameTime(bookMark.getIsSameTime())
                .build();
    }
}
