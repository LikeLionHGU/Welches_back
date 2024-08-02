package likelion.summer.welches.post.domain.repository;

import likelion.summer.welches.post.domain.entity.Post;
import likelion.summer.welches.temporaryPost.domain.entity.TemporaryPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByIsAllowed(Boolean isAllowed);

    @Query("select r from Post r where r.isConfirmed = :isConfirmed and r.bookMark.id = :bookmarkId")
    List<Post> findAllByIsConfirmedAndBookMarkId(Boolean isConfirmed, Long bookmarkId);

    @Query("select r from Post r where r.isConfirmed = :isConfirmed and r.isAllowed = :isApproved and r.bookMark.id = :bookmarkId")
    List<Post> findAllByIsConfirmedAndApprovedAndBookMarkId(Boolean isConfirmed, Boolean isApproved, Long bookmarkId);
}
