package com.queryholic.spring.vanila.mapper;

import com.queryholic.spring.vanila.model.User;

import java.util.List;

/**
 * @author : queryholic
 * @since : 2018. 9. 2.
 */
public interface UserMapper {
    List<User> selectUserList(User user);
}
