package likelion.summer.welches.post.application.service;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.post.domain.repository.PostRepository;
import likelion.summer.welches.post.presentation.response.PostGetAllResponse;
import likelion.summer.welches.post.presentation.response.PostGetResponse;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import likelion.summer.welches.userBookMark.domain.repository.UserBookMarkRepository;
import likelion.summer.welches.userProject.domain.entity.UserProject;
import likelion.summer.welches.userProject.domain.repository.UserProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BookMarkRepository bookMarkRepository;
    private final UserProjectRepository userProjectRepository;

    @Transactional
    public Long addPost(String userId, String contents, Long bookMarkId) {
        return postRepository.save(Post.toAdd(userRepository.findUserByUserId(userId), bookMarkRepository.findById(bookMarkId).orElse(null), contents)).getId();
    }

    @Transactional
    public Long confirmPost(Long postId, Boolean isAllowed, String rejectedReason) {
        Post post = postRepository.findById(postId).orElse(null);

        if(post != null) {
            post.setIsAllowed(isAllowed);
            post.setIsConfirmed(true);
            post.setRejectedReason(rejectedReason);
            postRepository.save(post);

            return post.getId();
        } else {
            return null;
        }

    }

    @Transactional
    public PostGetResponse getSelectedPost(Long postId) {
        Post post = postRepository.findById(postId).orElse(null);

        if(post != null) {
            return PostGetResponse.toResponse(post);
        } else {
            return null;
        }
    }

    @Transactional
    public List<PostGetAllResponse> getAllResponseList(String userId, Long bookMarkId) {
        BookMark bookmark = bookMarkRepository.findById(bookMarkId).orElse(null);

        if(bookmark != null) {
            Long projectId = bookmark.getProject().getId();

            UserProject userProject = userProjectRepository.findUserProjectByUserIdAndProjectId(userId, projectId);

            List<Post> postList;
            if(userProject != null || bookmark.getProject().getUser().getId().equals(userId)) { // 모든 히스토리를 볼 수 있어야 함
                postList = postRepository.findAll();

            } else { // 승인된 히스토리만 볼 수 있어야 함
                postList = postRepository.findAllByIsAllowed(true);
            }
            return postList.stream().map(PostGetAllResponse::toResponse).toList();
        } else {
            return null;
        }


    }
}
