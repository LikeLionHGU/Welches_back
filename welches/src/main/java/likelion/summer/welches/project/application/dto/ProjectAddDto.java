package likelion.summer.welches.project.application.dto;

import likelion.summer.welches.project.presentation.request.ProjectAddRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProjectAddDto {
    private String category;
    private String name;
    private String information;
    private String description;
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;
    private MultipartFile file;
    private String userId;
    private Boolean isRecruit;
    private String bigCategory;

    public static ProjectAddDto toAdd(ProjectAddRequest request, MultipartFile file, String userId) {
        return ProjectAddDto.builder()
                .category(request.getCategory())
                .name(request.getName())
                .description(request.getDescription())
                .information(request.getInformation())
                .isFinished(request.getIsFinished())
                .isPublic(request.getIsPublic())
                .maximumNumber(request.getMaximumNumber())
                .file(file)
                .bigCategory(request.getBigCategory())
                .userId(userId)
                .isRecruit(request.getIsRecruit())
                .build();
    }
}
