package com.test.project.model;

import com.test.project.entity.PrivateLetter;

/**
 * Created by handsome programmer.
 * User: chen
 * Date: 19-1-13
 * Time: 下午7:19
 * Description:
 *
 * @author chen
 */
public class PrivateLetterList  extends PrivateLetter {

    /**
     * 发件人用户名
     */
    private String username;

    @Override
    public String toString() {
        return "PrivateLetterList{" +
                "username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
