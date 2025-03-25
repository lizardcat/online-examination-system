

package xss.it.backend.service;

import xss.it.backend.entity.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    Optional<User> save(User user);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void delete(Long id);
}
