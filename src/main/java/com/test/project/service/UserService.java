package com.test.project.service;

import com.test.project.entity.User;
import com.test.project.model.PrivateLetterMessage;

import java.util.List;

/**
 * Created by handsome programmer.
 *
 * Date: 19-1-12
 * Time: 下午1:58
 * Description: 用户帐号的服务层
 *
 *
 */
public interface UserService {

    /**
     * 添加一个用户
     *
     * @param user 用户信息
     * @return 添加结果
     */
    int addUser(User user);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 修改结果
     */
    int modifyUser(User user);

    /**
     * 修改用户基本信息
     *
     * @param user 用户基本信息
     * @return 修改结果
     */
    int modifyUserMessage(User user);

    /**
     * 修改用户的身份等级
     *
     * @param user 用户信息
     * @return 修改结果
     */
    int modifyUserIdentity(User user);


    /**
     * 找回密码
     *
     * @param email 用户邮箱
     * @return 密码
     */
    String lookForPassword(String email);

    /**
     * 签到
     *
     * @param id 用户id
     * @return 签到结果
     */
    int signIn(int id);

    /**
     * 发送私信
     *
     * @param from    发送者的id
     * @param to      接受者的id
     * @param message 私信内容
     * @return 发送结果
     */
    int sendPrivateLetter(int from, int to, String message);


    /**
     * 获取一个人的所有私信
     *
     * @param receiver 收信人Id
     * @return 列表
     */
    List<PrivateLetterMessage> getAllMessageById(int receiver);

    /**
     * 更新私信查看状态
     *
     * @param id 私信id
     * @return 更新结果
     */
    int updatePrivateLetter(int id);

    /**
     * 获取所有的用户列表
     *
     * @return 用户列表
     */
    List<User> getAllUser();

    /**
     * 根据id查询用户
     *
     * @param id id
     * @return 查询结果
     */
    User getUserById(int id);

    /**
     * 更新用户密码
     *
     * @param id          用户id
     * @param newPassword 新密码
     * @return 更新结果
     */
    int modifyUserPassword(int id, String newPassword);
}
