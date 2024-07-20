package likelion.summer.welches.userBookMark.application.service;

import likelion.summer.welches.bookMark.domain.repository.BookMarkRepository;
import likelion.summer.welches.user.domain.repository.UserRepository;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import likelion.summer.welches.userBookMark.domain.repository.UserBookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserBookMarkService {
    private final UserBookMarkRepository userBookMarkRepository;
    private final UserRepository userRepository;
    private final BookMarkRepository bookMarkRepository;

    @Transactional
    public Long addUserBookMark(String userId, Long bookMarkId) {
        return userBookMarkRepository.save(UserBookMark.toAdd(userRepository.findUserByUserId(userId), bookMarkRepository.findById(bookMarkId).orElse(null))).getId();
    }
}
