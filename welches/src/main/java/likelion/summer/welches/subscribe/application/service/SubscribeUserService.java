package likelion.summer.welches.subscribe.application.service;

import likelion.summer.welches.subscribe.domain.entity.SubscribeUser;
import likelion.summer.welches.subscribe.domain.repository.SubscribeUserRepository;
import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscribeUserService {
    private final UserRepository userRepository;
    private final SubscribeUserRepository subscribeUserRepository;

    @Transactional
    public Long addSubscribeUser(String userId, String subscribeUserId) {
        User user = userRepository.findUserByUserId(userId);

        return subscribeUserRepository.save(SubscribeUser.toAdd(user, subscribeUserId)).getId();
    }
}
