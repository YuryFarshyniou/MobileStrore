package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.UserExample;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ServiceExample {
    private List<UserExample> list = new ArrayList<>(Arrays.asList(
            new UserExample("Yura", 23),
            new UserExample("Vika",20)
    ));

    public List<UserExample> getAllUsers() {
        return this.list;
    }
}
