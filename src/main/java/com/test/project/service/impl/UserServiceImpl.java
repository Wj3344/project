package com.test.project.service.impl;

import com.test.project.entity.User;
import com.test.project.mapper.UserMapper;
import com.test.project.model.PrivateLetterMessage;
import com.test.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-14
 * Time: 下午4:42
 * Description:
 *
 * @author chen
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public int modifyUser(User user) {
        return 0;
    }

    @Override
    public String lookForPassword(String email) {
        return null;
    }

    @Override
    public int signIn(int id) {
        return 0;
    }

    @Override
    public int sendPrivateLetter(int from, int to, String message) {
        return 0;
    }

    @Override
    public List<PrivateLetterMessage> getAllMessageById(int receiver) {
        return null;
    }

    @Override
    public int updatePrivateLetter(int id) {
        return 0;
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUserOfNotAdmin();
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
