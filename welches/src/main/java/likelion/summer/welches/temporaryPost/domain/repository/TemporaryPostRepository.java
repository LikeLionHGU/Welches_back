package likelion.summer.welches.temporaryPost.domain.repository;


import likelion.summer.welches.temporaryPost.domain.entity.TemporaryPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TemporaryPostRepository extends JpaRepository<TemporaryPost, Long> {

    @Query("select r from TemporaryPost r where r.user.id = :userId and r.bookMark.id = :bookMarkId")
    TemporaryPost findTemporaryPostByUserIdAndBookMarkId(String userId, Long bookMarkId);
}
