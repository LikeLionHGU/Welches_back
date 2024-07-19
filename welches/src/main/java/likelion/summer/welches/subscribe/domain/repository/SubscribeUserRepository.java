package likelion.summer.welches.subscribe.domain.repository;

import likelion.summer.welches.subscribe.domain.entity.SubscribeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscribeUserRepository extends JpaRepository<SubscribeUser, Long> {
}
