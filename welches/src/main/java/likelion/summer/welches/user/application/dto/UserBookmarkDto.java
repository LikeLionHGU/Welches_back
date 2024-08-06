package likelion.summer.welches.user.application.dto;

import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBookmarkDto {
    private String id;
    private String name;
    private String imageAddress;

    public static UserBookmarkDto toResponse(UserBookMark userBookMark) {
        return UserBookmarkDto.builder()
                .id(userBookMark.getUser().getId())
                .imageAddress(userBookMark.getUser().getProfileImageAddress())
                .name(userBookMark.getUser().getName())
                .build();
    }
}
