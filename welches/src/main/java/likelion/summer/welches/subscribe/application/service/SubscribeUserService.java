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

    @Transactional
    public Long deleteSubscribeUser(String userId, String subscribeUserId) {
        SubscribeUser subscribeUser = subscribeUserRepository.findSubscribeUserByUserIdAndSubscribeUserId(userId, subscribeUserId);
        Long response = subscribeUser.getId();
        subscribeUserRepository.delete(subscribeUser);

        return response;
    }

    @Transactional
    public Long switchSubscribe(String userId, String myId) {
        SubscribeUser subscribeUser = subscribeUserRepository.findSubscribeUserByUserIdAndSubscribeUserId(myId, userId);
        Long id;
        if(subscribeUser != null) {
            id = subscribeUser.getId();
            subscribeUserRepository.delete(subscribeUser);

        } else {

            User user = userRepository.findUserByUserId(myId);
            id = subscribeUserRepository.save(SubscribeUser.toAdd(user, userId)).getId();
        }

        return id;
    }
}
