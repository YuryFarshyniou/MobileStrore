package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.repository.UserRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserService implements IService<User> {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        logger.info("User {} was successfully found", user.getUserName());
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        logger.info("Users was successfully found");
        return users;
    }

    @Transactional
    public User save(User user) {
        User userToDb = userRepository.saveAndFlush(user);
        logger.info("User {} successfully added to db", user.getId());
        return userToDb;
    }

    @Transactional
    public void deleteById(long id) {
        userRepository.deleteById(id);
        logger.info("User with id {} was successfully deleted", id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> findAllWithPagination(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        logger.info("All users in db was successfully found");
        return users;
    }
}
