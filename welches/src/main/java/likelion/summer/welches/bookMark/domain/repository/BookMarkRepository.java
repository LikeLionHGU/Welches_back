package likelion.summer.welches.bookMark.domain.repository;

import likelion.summer.welches.bookMark.domain.entity.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMarkRepository extends JpaRepository<BookMark, Long> {
}
