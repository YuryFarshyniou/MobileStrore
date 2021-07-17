package by.yurachel.springapp.controller;

import by.yurachel.springapp.model.UserExample;
import by.yurachel.springapp.service.impl.ServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/example")
public class ControllerExample {


    private final ServiceExample serviceExample;

    @Autowired
    public ControllerExample(ServiceExample serviceExample) {
        this.serviceExample = serviceExample;
    }

    @GetMapping
    public String ex() {
        return "example";
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody()
    public ResponseEntity<List<UserExample>> getAllUsers() {
        List<UserExample> list = serviceExample.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


}
