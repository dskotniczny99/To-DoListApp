package pl.skotniczny.app.user.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.skotniczny.app.user.model.User;
import pl.skotniczny.app.user.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }




}
