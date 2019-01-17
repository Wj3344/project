package com.test.project.model;

import com.test.project.entity.PrivateLetter;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-12
 * Time: 下午4:13
 * Description: 私信查看类
 *
 *
 */
public class PrivateLetterMessage extends PrivateLetter {
    /**
     * 发件人昵称
     */
    private String authorNickName;

    public String getAuthorNickName() {
        return authorNickName;
    }

    public void setAuthorNickName(String authorNickName) {
        this.authorNickName = authorNickName;
    }
}
