package likelion.summer.welches.post.application.service;

import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.post.domain.repository.PostRepository;
import likelion.summer.welches.post.presentation.response.PostGetResponse;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final BookMarkRepository bookMarkRepository;

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
}
