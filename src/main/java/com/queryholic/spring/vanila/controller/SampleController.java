package com.queryholic.spring.vanila.controller;

import com.queryholic.spring.vanila.model.User;
import com.queryholic.spring.vanila.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author : queryholic
 * @since : 2018. 9. 2.
 */
@Slf4j
@Controller
public class SampleController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        List<User> users = userService.getUsers(new User());
        log.info("### users: {}", users);

        return "index";
    }
}
