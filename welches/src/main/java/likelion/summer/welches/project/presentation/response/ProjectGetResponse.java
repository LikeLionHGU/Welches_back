package likelion.summer.welches.project.presentation.response;

import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.user.application.dto.UserInformationDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectGetResponse {
    private Long id;
    private String imageAddress;
    private String category;
    private String name;
    private String information;
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;
    private String ownerId;
    private List<UserInformationDto> userApplicationList;
    private List<UserInformationDto> userProjectList;


    public static ProjectGetResponse toResponse(Project project) {
        return ProjectGetResponse.builder()
                .id(project.getId())
                .imageAddress(project.getImageAddress())
                .category(project.getCategory())
                .name(project.getName())
                .information(project.getInformation())
                .isPublic(project.getIsPublic())
                .maximumNumber(project.getMaximumNumber())
                .isFinished(project.getIsFinished())
                .userProjectList(project.getUserProjectList().stream().map(UserInformationDto::toResponse).toList())
                .userApplicationList(project.getUserApplicationList().stream().map(UserInformationDto::toResponse).toList())
                .build();

    }
}
