package likelion.summer.welches.project.presentation.response;

import likelion.summer.welches.bookMark.application.dto.BookMarkDto;
import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.project.domain.entity.Project;
import likelion.summer.welches.projectComment.application.dto.ProjectCommentDto;
import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.user.application.dto.UserInformationDto;
import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userProject.domain.entity.UserProject;
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
    private String description;
    private Boolean isPublic;
    private Long maximumNumber;
    private Boolean isFinished;
    private String ownerId;
    private Boolean isLiked;
    private Long likeCount;
    private Boolean isOwner;
    private Boolean isParticipate;
    private Boolean isRecruit;
    private Boolean isApplication;
    private List<UserInformationDto> userProjectList;
    private List<UserInformationDto> userApplicationList;
    private List<BookMarkDto> bookMarkList;
    private List<ProjectCommentDto> commentList;


    public static ProjectGetResponse toResponse(Project project, String userId) {
        Boolean temp = Boolean.FALSE;

        List<ProjectLike> projectLikeList = project.getProjectLikeList();
        for(ProjectLike p : projectLikeList) {
            if(p.getUser().getId().equals(userId)) {
                temp = Boolean.TRUE;
                break;
            }
        }

        Boolean isOwner = Boolean.FALSE;
        Boolean isParticipate = Boolean.FALSE;
        Boolean isApplication = Boolean.FALSE;

        if(project.getUser().getId().equals(userId)) {
            isOwner = Boolean.TRUE;
            isParticipate = Boolean.TRUE;
        }

        List<UserProject> userProjectList = project.getUserProjectList();
        for(UserProject p : userProjectList) {
            if(p.getUser().getId().equals(userId)) {
                isParticipate = Boolean.TRUE;
            }
        }

        List<UserApplication> userApplications = project.getUserApplicationList();
        for(UserApplication p : userApplications) {
            if(p.getUser().getId().equals(userId)) {
                isApplication = Boolean.TRUE;
            }
        }






        return ProjectGetResponse.builder()
                .id(project.getId())
                .isRecruit(project.getIsRecruit())
                .description(project.getDescription())
                .likeCount(Long.valueOf(project.getProjectLikeList().size()))
                .isLiked(temp)
                .isOwner(isOwner)
                .isParticipate(isParticipate)
                .isApplication(isApplication)
                .imageAddress(project.getImageAddress())
                .category(project.getCategory())
                .name(project.getName())
                .information(project.getInformation())
                .isPublic(project.getIsPublic())
                .maximumNumber(project.getMaximumNumber())
                .isFinished(project.getIsFinished())
                .userProjectList(project.getUserProjectList().stream().map(UserInformationDto::toResponse).toList())
                .userApplicationList(project.getUserApplicationList().stream().map(UserInformationDto::toResponse).toList())
                .bookMarkList(project.getBookMarkList().stream().map((BookMark bookMark) -> BookMarkDto.toResponse(bookMark, userId)).toList())
                .commentList(project.getProjectCommentList().stream().map((ProjectComment projectComment) -> ProjectCommentDto.toResponse(projectComment, userId)).toList())
                .build();

    }
}
