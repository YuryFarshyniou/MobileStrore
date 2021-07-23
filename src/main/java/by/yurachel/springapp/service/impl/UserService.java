package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.repository.UserRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
public class UserService implements IService<User> {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        logger.info("User {} was successfully found", user.getUserName());
        return user;
    }

    @Transactional
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        logger.info("Users was successfully found");
        return users;
    }

    @Transactional
    public User save(User user) {
        User userToDb = userRepository.save(user);
        logger.info("User was successfully added to db");
        return userToDb;
    }

    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
        logger.info("User with id {} was successfully deleted", id);
    }

    @Override
    @Transactional
    public Page<User> findAllPhones(Pageable pageable) {
        return null;
    }
}
