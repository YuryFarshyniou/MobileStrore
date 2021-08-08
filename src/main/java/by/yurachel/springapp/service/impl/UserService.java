package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.user.impl.User;
import by.yurachel.springapp.repository.UserRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserService implements IService<User> {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "userId", key = "#id")
    public User findById(long id) {
        User user = userRepository.findById(id).orElse(null);
        logger.info("User {} was successfully found", user.getUserName());
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "usersF")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        logger.info("Users was successfully found");
        return users;
    }

    @Override
    @CachePut(cacheNames = "userId", key = "#user.id")
    public User save(User user) {
        User userToDb = userRepository.saveAndFlush(user);
        logger.info("User {} successfully added to db", user.getId());
        return userToDb;
    }

    @Override
    @CacheEvict(cacheNames = "userId", key = "#id")
    public void deleteById(long id) {
        userRepository.deleteById(id);
        logger.info("User with id {} was successfully deleted", id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "usersP")
    public Page<User> findAllWithPagination(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        logger.info("All users in db was successfully found");
        return users;
    }
}
