package likelion.summer.welches.userBookMark.domain.repository;

import likelion.summer.welches.userApplication.domain.entity.UserApplication;
import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserBookMarkRepository extends JpaRepository<UserBookMark, Long> {

    @Query("select r from UserBookMark r where r.user.id = :userId and r.bookMark.id = :bookMarkId")
    UserBookMark findUserBookMarkByUserIdAndBookMarkId(String userId, Long bookMarkId);
}
