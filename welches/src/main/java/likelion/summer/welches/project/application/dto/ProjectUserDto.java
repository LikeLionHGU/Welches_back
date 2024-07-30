package likelion.summer.welches.project.application.dto;

import likelion.summer.welches.project.domain.entity.Project;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserDto {
    private Long id;
    private String imageAddress;
    private String name;
    private Boolean isOwner;
    private Boolean isFinished;

    public static ProjectUserDto toResponse(Project project, String userId) {
        Boolean temp;
        if(userId.equals(project.getUser().getId())) {
            temp = Boolean.TRUE;
        } else {
            temp = Boolean.FALSE;
        }

        return ProjectUserDto.builder()
                .id(project.getId())
                .imageAddress(project.getImageAddress())
                .name(project.getName())
                .isOwner(temp)
                .isFinished(project.getIsFinished())
                .build();
    }
}
