package likelion.summer.welches.userBookMark.domain.repository;

import likelion.summer.welches.userBookMark.domain.entity.UserBookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookMarkRepository extends JpaRepository<UserBookMark, Long> {
}
