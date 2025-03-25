

package xss.it.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import xss.it.backend.entity.User;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
