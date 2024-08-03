package likelion.summer.welches.project.presentation.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectAddRequest {
    private String category;
    private String name;
    private String information; // 책 정보
    private String description; // 책 한줄 소개
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;
    private Boolean isRecruit;
    private String bigCategory;
}
