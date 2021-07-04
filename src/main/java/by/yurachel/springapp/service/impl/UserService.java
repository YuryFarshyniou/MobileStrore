package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.repository.UserRepository;
import by.yurachel.springapp.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService implements IService<User> {

    private final UserRepository userRepository;

    public UserService(by.yurachel.springapp.repository.UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }
}
