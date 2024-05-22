package pl.skotniczny.app.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserDeleter {

    private final UserRepository userRepository;

    public User deleteUserByUsername(String username) {
        return userRepository.deleteByUsername(username);
    }

}
