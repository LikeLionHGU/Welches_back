package likelion.summer.welches.post.domain.repository;

import likelion.summer.welches.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByIsAllowed(Boolean isAllowed);
}
