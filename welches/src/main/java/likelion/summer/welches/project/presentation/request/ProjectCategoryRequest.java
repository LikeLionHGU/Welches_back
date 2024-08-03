package likelion.summer.welches.project.presentation.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProjectCategoryRequest {
    private Boolean isFinished;
    private Boolean isRecruit;
    private String category;
    private String bigCategory;

    public static ProjectCategoryRequest toRequest(String category, Boolean isFinished, Boolean isRecruit) {
        return ProjectCategoryRequest.builder()
                .category(category)
                .isFinished(isFinished)
                .isRecruit(isRecruit)
                .build();
    }

    public static ProjectCategoryRequest toBigRequest(String category, Boolean isFinished, Boolean isRecruit) {
        return ProjectCategoryRequest.builder()
                .bigCategory(category)
                .isFinished(isFinished)
                .isRecruit(isRecruit)
                .build();
    }
}
