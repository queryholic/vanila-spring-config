package com.queryholic.spring.vanila.service;

import com.queryholic.spring.vanila.mapper.UserMapper;
import com.queryholic.spring.vanila.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : queryholic
 * @since : 2018. 9. 2.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public List<User> getUsers(User user) {
        return userMapper.selectUserList(user);
    }
}
