package likelion.summer.welches.bookMark.application.dto;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.user.application.dto.UserBookmarkDto;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

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
    private Boolean canEdit; // 사용자가 권한이 있는지 여부를 확인함
    private Boolean isCurrentEdit;

    private List<UserBookmarkDto> user;

    public static BookMarkDto toResponse(BookMark bookMark, String userId) {
        List<UserBookMark> bookMarkList = bookMark.getUserBookMarkList();
        Boolean temp = Boolean.FALSE;

        for(UserBookMark p : bookMarkList) {
            if(p.getUser().getId().equals(userId)) {
                temp = Boolean.TRUE;
                break;
            }
        }

        return BookMarkDto.builder()
                .id(bookMark.getId())
                .canEdit(temp)
                .isCurrentEdit(bookMark.getIsCurrentEdit())
                .name(bookMark.getName())
                .isSameTime(bookMark.getIsSameTime())
                .isShared(bookMark.getIsShared())
                .user(bookMark.getUserBookMarkList().stream().map(UserBookmarkDto::toResponse).toList())
                .build();

    }
}
