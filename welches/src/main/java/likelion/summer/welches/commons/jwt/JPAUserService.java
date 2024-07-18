package likelion.summer.welches.commons.jwt;

import likelion.summer.welches.user.domain.entity.User;
import likelion.summer.welches.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JPAUserService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("Invalid authentication!")
        );

        return new CustomUserDetails(user);
    }
}
