package likelion.summer.welches.subscribe.domain.repository;

import likelion.summer.welches.projectLike.domain.entity.ProjectLike;
import likelion.summer.welches.subscribe.domain.entity.SubscribeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeUserRepository extends JpaRepository<SubscribeUser, Long> {

    @Query("select r from SubscribeUser r where r.user.id = :userId and r.subscribeUserId = :subscribeUserId")
    SubscribeUser findSubscribeUserByUserIdAndSubscribeUserId(String userId, String subscribeUserId);
}
