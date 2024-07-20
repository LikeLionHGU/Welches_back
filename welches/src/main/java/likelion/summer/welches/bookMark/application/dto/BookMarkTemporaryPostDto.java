package likelion.summer.welches.bookMark.application.dto;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookMarkTemporaryPostDto {
    private Long id;

    private String name;

    public static BookMarkTemporaryPostDto toReturn(BookMark bookMark) {
        return BookMarkTemporaryPostDto.builder()
                .id(bookMark.getId())
                .name(bookMark.getName())
                .build();
    }
}
