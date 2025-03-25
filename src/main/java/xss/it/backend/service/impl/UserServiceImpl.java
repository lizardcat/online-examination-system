

package xss.it.backend.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xss.it.backend.entity.User;
import xss.it.backend.repo.UserRepo;
import xss.it.backend.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo repo;

    @Override
    public Optional<User> save(User user) {
        return Optional.of(repo.save(user));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<User> userOpt = repo.findByUsername(username);
        return userOpt.isPresent() && userOpt.get().getPassword().equals(password);
    }

    @Override
    public boolean registerUser(String username, String password) {
        if (repo.findByUsername(username).isPresent()) {
            return false; // Username already exists
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("student"); // Default role; change if needed

        repo.save(user);
        return true;
    }
}
