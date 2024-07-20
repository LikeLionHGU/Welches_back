package likelion.summer.welches.post.application.service;

import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.post.domain.repository.PostRepository;
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
}
