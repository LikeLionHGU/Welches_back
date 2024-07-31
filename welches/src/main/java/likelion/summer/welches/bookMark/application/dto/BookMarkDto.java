package likelion.summer.welches.bookMark.application.dto;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkDto {
    private Long id;

    private String name;

    private Boolean isShared;

    private Boolean isSameTime;

    public static BookMarkDto toResponse(BookMark bookMark) {
        return BookMarkDto.builder()
                .id(bookMark.getId())
                .name(bookMark.getName())
                .isSameTime(bookMark.getIsSameTime())
                .isShared(bookMark.getIsShared())
                .build();

    }
}
