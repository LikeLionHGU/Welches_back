package likelion.summer.welches.temporaryPost.application.service;

import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.temporaryPost.domain.entity.TemporaryPost;
import likelion.summer.welches.temporaryPost.domain.repository.TemporaryPostRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TemporaryPostService {
    private final TemporaryPostRepository temporaryPostRepository;
    private final UserRepository userRepository;
    private final BookMarkRepository bookMarkRepository;

    @Transactional
    public Long upload(String userId, String contents, Long bookMarkId) {
        TemporaryPost temporaryPost = temporaryPostRepository.findTemporaryPostByUserIdAndBookMarkId(userId, bookMarkId);

        if(temporaryPost != null) {
            temporaryPostRepository.delete(temporaryPost);
            return temporaryPostRepository.save(TemporaryPost.toAdd(userRepository.findUserByUserId(userId), bookMarkRepository.findById(bookMarkId).orElse(null), contents)).getId();


        } else {
            return temporaryPostRepository.save(TemporaryPost.toAdd(userRepository.findUserByUserId(userId), bookMarkRepository.findById(bookMarkId).orElse(null), contents)).getId();
        }
    }
}
