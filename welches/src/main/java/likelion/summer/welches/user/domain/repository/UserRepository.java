package likelion.summer.welches.user.domain.repository;

import likelion.summer.welches.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {
//    @Query("select r from User r where r.id = :userId")
//    List<User> findUserListByUserId(String userId);

    @Query("select r from User r where r.id = :userId")
    User findUserByUserId(String userId);

    @Query("select r from User r where r.email = :email")
    User findUserByEmail(String email);

}
