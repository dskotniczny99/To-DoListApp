package pl.skotniczny.app.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.model.UserNotFoundException;
import pl.skotniczny.app.user.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRetriever {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username: " + username + " not found"));
    }
}
