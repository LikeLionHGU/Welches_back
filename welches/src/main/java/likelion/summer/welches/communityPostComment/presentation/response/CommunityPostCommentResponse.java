package likelion.summer.welches.communityPostComment.presentation.response;

import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import likelion.summer.welches.communityPostCommentLike.domain.entity.CommunityPostCommentLike;
import likelion.summer.welches.user.application.dto.UserCommentDto;
import likelion.summer.welches.user.application.dto.UserCommunityResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostCommentResponse {
    private Long id;

    private String contents;
    private LocalDateTime createdDate;
    private UserCommentDto user;
    private Long likeCount;
    private Boolean isLike;
    private Boolean isOwner;

    public static CommunityPostCommentResponse toResponse(CommunityPostComment comment, String userId) {
        Boolean owner = Boolean.FALSE;
        Boolean like = Boolean.FALSE;

        if(comment.getUser().getId().equals(userId)) owner = Boolean.TRUE;

        List<CommunityPostCommentLike> list = comment.getCommunityPostCommentLikeList();
        for(CommunityPostCommentLike p : list) {
            if (p.getUser().getId().equals(userId)) {
                like = Boolean.TRUE;
                break;
            }
        }

        return CommunityPostCommentResponse.builder()
                .id(comment.getId())
                .likeCount(Long.valueOf(comment.getCommunityPostCommentLikeList().size()))
                .contents(comment.getContents())
                .isLike(like)
                .isOwner(owner)
                .createdDate(comment.getCreatedDate())
                .user(UserCommentDto.toResponse(comment.getUser()))
                .build();
    }
}
