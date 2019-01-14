package com.test.project.service.impl;

import com.test.project.entity.PrivateLetter;
import com.test.project.entity.User;
import com.test.project.mapper.PrivateLetterMapper;
import com.test.project.mapper.UserMapper;
import com.test.project.model.PrivateLetterList;
import com.test.project.service.PrivateLetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-13
 * Time: 下午7:18
 * Description:
 *
 * @author chen
 */
@Service
public class PrivateLetterServiceImpl implements PrivateLetterService {

    private PrivateLetterMapper privateLetterMapper;

    private UserMapper userMapper;

    @Override
    public List<PrivateLetterList> getByReceiverId(int receiver) {
        List<PrivateLetter> allNoReadMessage = privateLetterMapper.findAllMessage(receiver);
        List<PrivateLetterList> privateLetterLists = new ArrayList<>(allNoReadMessage.size());
        for (PrivateLetter letter : allNoReadMessage) {
            PrivateLetterList list = new PrivateLetterList();
            list.setId(letter.getId());
            list.setAuthor(letter.getAuthor());
            list.setReceiver(letter.getReceiver());
            list.setMessage(letter.getMessage());
            list.setTime(letter.getTime());
            list.setSign(letter.getSign());
            User user = userMapper.selectByPrimaryKey(list.getAuthor());
            list.setUsername(user.getUsername());
            privateLetterLists.add(list);
        }
        return privateLetterLists;
    }

    @Override
    public int mark(int id) {
        PrivateLetter p = new PrivateLetter();
        p.setId(id);
        p.setSign(true);
        return privateLetterMapper.updateSign(p);
    }

    @Override
    public int delete(int id) {
        return privateLetterMapper.deleteByPrimaryKey(id);
    }

    @Autowired
    public void setPrivateLetterMapper(PrivateLetterMapper privateLetterMapper) {
        this.privateLetterMapper = privateLetterMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
