package likelion.summer.welches.projectComment.presentation.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectCommentRequest {
    private String contents;
}
