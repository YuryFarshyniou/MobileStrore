package by.yurachel.springapp.initDb.initUsers;

import by.yurachel.springapp.model.user.Role;
import by.yurachel.springapp.model.user.Status;
import by.yurachel.springapp.model.user.User;
import by.yurachel.springapp.service.userService.IUserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitUsers {
    private final IUserService<User> serviceUser;

    public InitUsers(IUserService<User> serviceUser) {
        this.serviceUser = serviceUser;
    }

    public void initUsers(ConfigurableApplicationContext context) {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        User user = new User();
        user.setStatus(Status.ACTIVE);
        user.setRole(Role.USER);
        user.setUserName("user");
        user.setEmail("user@mail.ru");
        user.setPassword(encoder.encode("user"));

        User admin = new User();
        admin.setEmail("admin@mail.ru");
        admin.setUserName("admin");
        admin.setStatus(Status.ACTIVE);
        admin.setRole(Role.ADMIN);
        admin.setPassword(encoder.encode("admin"));
        serviceUser.save(user);
        serviceUser.save(admin);
    }

}
