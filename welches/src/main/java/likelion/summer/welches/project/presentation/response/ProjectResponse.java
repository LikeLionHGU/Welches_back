package likelion.summer.welches.project.presentation.response;

import likelion.summer.welches.project.domain.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ProjectResponse {
    private Long id;
    private String imageAddress;
    private String category;
    private String name;
    private String information;
    private String description;
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;
    private Boolean isOwner;
    private String ownerId;
    private Long likeCount;
    private Boolean isRecruit;
    private String ownerName;
    private Long people;

    public static ProjectResponse toResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .ownerName(project.getUser().getName())
                .people(Long.valueOf(project.getUserProjectList().size()))
                .description(project.getDescription())
                .imageAddress(project.getImageAddress())
                .category(project.getCategory())
                .name(project.getName())
                .information(project.getInformation())
                .isPublic(project.getIsPublic())
                .maximumNumber(project.getMaximumNumber())
                .isFinished(project.getIsFinished())
                .ownerId(project.getUser().getId())
                .likeCount(Long.valueOf(project.getProjectLikeList().size()))
                .isRecruit(project.getIsRecruit())
                .build();
    }
}
