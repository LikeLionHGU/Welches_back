package likelion.summer.welches.communityPost.presentation.response;

import likelion.summer.welches.communityPost.domain.entity.CommunityPost;
import likelion.summer.welches.communityPostComment.domain.entity.CommunityPostComment;
import likelion.summer.welches.communityPostComment.presentation.response.CommunityPostCommentResponse;
import likelion.summer.welches.communityPostLike.domain.entity.CommunityPostLike;
import likelion.summer.welches.user.application.dto.UserCommunityResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityPostResponse {
    private Long id;
    private String contents;
    private String title;
    private UserCommunityResponse writer; // 작성자
    private Long commentCount; // 댓글 개수
    private Long likeCount; // 좋아요 개수
    private Boolean isLike; // 좋아요 달았는지 여부
    private LocalDateTime createdDate;
    private String imageAddress;
    private List<CommunityPostCommentResponse> commentList;

    public static CommunityPostResponse toResponse(CommunityPost communityPost, String userId) {
        Boolean temp = Boolean.FALSE;
        List<CommunityPostLike> likeList = communityPost.getCommunityPostLikeList();

        for(CommunityPostLike like : likeList) {
            if(like.getUser().getId().equals(userId)) {
                temp = Boolean.TRUE;
                break;
            }
        }
        return CommunityPostResponse.builder()
                .id(communityPost.getId())
                .commentList(communityPost.getCommunityPostComment().stream().map((CommunityPostComment comment) -> CommunityPostCommentResponse.toResponse(comment, userId)).toList())
                .imageAddress(communityPost.getImageUrl())
                .createdDate(communityPost.getCreatedDate())
                .title(communityPost.getTitle())
                .contents(communityPost.getContents())
                .commentCount(Long.valueOf(communityPost.getCommunityPostComment().size()))
                .likeCount(Long.valueOf(communityPost.getCommunityPostLikeList().size()))
                .isLike(temp)
                .writer(UserCommunityResponse.toResponse(communityPost.getUser()))
                .build();

    }
}
