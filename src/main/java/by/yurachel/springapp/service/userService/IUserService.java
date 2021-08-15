package by.yurachel.springapp.service.userService;

import by.yurachel.springapp.service.IService;

public interface IUserService<T> extends IService<T> {
    boolean findUserByUsername(String username);

    boolean findUserByEmail(String email);


}
