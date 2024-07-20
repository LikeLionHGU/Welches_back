package likelion.summer.welches.bookMark.presentation.response;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import lombok.*;

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

    public static BookMarkResponse toResponse(BookMark bookMark) {
        return BookMarkResponse.builder()
                .id(bookMark.getId())
                .name(bookMark.getName())
                .isShared(bookMark.getIsShared())
                .isSameTime(bookMark.getIsSameTime())
                .build();
    }
}
