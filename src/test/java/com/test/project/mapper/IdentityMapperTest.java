package com.test.project.mapper;

import com.test.project.entity.Identity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-10
 * Time: 下午7:49
 * Description:
 *
 * @author chen
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IdentityMapperTest {
    private IdentityMapper identityMapper;

    @Test
    public void tset() {
        for (int i = 2; i < 5; i++) {
            Identity identity = new Identity();
            identity.setDescription("admin" + i);
            int insert = identityMapper.insert(identity);
            System.out.println(insert);
        }
    }

    @Test
    public void test2() {
        Identity identity = new Identity();
        identity.setId(2);
        identity.setDescription("admin222");
        int i = identityMapper.updateByPrimaryKey(identity);
        System.out.println(i);
    }

    @Test
    public void test3() {
        Identity identity = identityMapper.selectByPrimaryKey(3);
        System.out.println(identity);
        List<Identity> all = identityMapper.getAll();
        for (Identity i : all) {
            System.out.println(i);
        }
        int i = identityMapper.deleteByPrimaryKey(4);
        System.out.println(i);
    }

    @Autowired
    public void setIdentityMapper(IdentityMapper identityMapper) {
        this.identityMapper = identityMapper;
    }
}