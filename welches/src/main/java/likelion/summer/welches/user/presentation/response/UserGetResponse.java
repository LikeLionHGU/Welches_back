package likelion.summer.welches.user.presentation.response;

import likelion.summer.welches.project.application.dto.ProjectUserDto;
import likelion.summer.welches.user.application.dto.UserSubscribeDto;
import likelion.summer.welches.user.domain.entity.User;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetResponse {
    private String name;
    private String information;
    private String email;
    private List<UserSubscribeDto> subscribeUser;
    private List<ProjectUserDto> progressProjectList;
    private List<ProjectUserDto> finishedProjectList;
    private List<ProjectUserDto> likedProjectList;
    private Long progressProjects;
    private Long finishedProjects;
    private Long subscribeUserCounts;


    public static UserGetResponse toResponse(User user, List<UserSubscribeDto> subscribeUser, List<ProjectUserDto> progressProjectList, List<ProjectUserDto> finishedProjectList, List<ProjectUserDto> likedProjectList) {



        return UserGetResponse.builder()
                .name(user.getName())
                .information(user.getInformation())
                .email(user.getEmail())
                .subscribeUser(subscribeUser)
                .progressProjectList(progressProjectList)
                .finishedProjectList(finishedProjectList)
                .likedProjectList(likedProjectList)
                .progressProjects(Long.valueOf(progressProjectList.size()))
                .finishedProjects(Long.valueOf(finishedProjectList.size()))
                .subscribeUserCounts(Long.valueOf(subscribeUser.size()))
                .build();
    }
}
