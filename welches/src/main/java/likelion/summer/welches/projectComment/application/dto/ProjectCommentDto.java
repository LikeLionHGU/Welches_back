package likelion.summer.welches.projectComment.application.dto;


import likelion.summer.welches.projectComment.domain.entity.ProjectComment;
import likelion.summer.welches.projectCommentLike.domain.entity.ProjectCommentLike;
import likelion.summer.welches.user.application.dto.UserCommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCommentDto {
    private Long id;
    private String contents;
    private LocalDateTime createdDate;
    private Long commentLikeCount;
    private UserCommentDto user;
    private Boolean isLiked; // 현재 사용자가 좋아요를 눌렀는지 여부

    public static ProjectCommentDto toResponse(ProjectComment projectComment, String userId) {
        Boolean temp = Boolean.FALSE;

        List<ProjectCommentLike> projectCommentLikeList = projectComment.getProjectCommentLikeList();
        for(ProjectCommentLike p : projectCommentLikeList) {
            if(p.getUser().getId().equals(userId)) {
                temp = Boolean.TRUE;
                break;
            }
        }
        return ProjectCommentDto.builder()
                .id(projectComment.getId())
                .user(UserCommentDto.toResponse(projectComment.getUser()))
                .contents(projectComment.getContents())
                .createdDate(projectComment.getCreatedDate())
                .commentLikeCount(Long.valueOf(projectComment.getProjectCommentLikeList().size()))
                .isLiked(temp)
                .build();
    }
}
