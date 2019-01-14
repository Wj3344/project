package com.test.project.mapper;

import com.test.project.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {
    /**
     * 根据id删除一条记录
     *
     * @param id id
     * @return 删除结果
     */
    @Delete("delete from user where id = #{id};")
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 插入结果
     */
    @Insert("insert into user "
            + "(username, password, nickname, school, studentId, email, picture, signature, identity) values"
            + " (#{username}, #{password}, #{nickname}, #{school}, #{studentId}, #{email}, #{picture}, #{signature}, #{identity});")
    int insert(User record);

    /**
     * 根据id查找用户
     *
     * @param id id
     * @return 查找结果
     */
    @Select("select id, username, password, nickname, school, studentId, email, picture, signature, identity,"
            + " point from user where id = #{id};")
    User selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新记录
     *
     * @param record 新的记录
     * @return 更新结果
     */
    @Update("update user set username = #{username}, password = #{password}, nickname = #{nickname},"
            + " school = #{school}, email = #{email}, picture = #{picture}, signature = #{signature},"
            + " identity = #{identity} where id = #{id} and studentId = #{studentId}")
    int updateByPrimaryKey(User record);

    /**
     * 增加积分
     *
     * @param id       帐号id
     * @param integral 积分数量
     * @return 修改结果
     */
    @Update("update user set point = point + #{integral} where id = #{id};")
    int increaseIntegral(int id, int integral);

    /**
     * 减少积分
     *
     * @param id       账户id
     * @param integral 积分数目
     * @return 修改结果
     */
    @Update("update user set point = point - #{integral} where id = #{id};")
    int reduceIntegral(int id, int integral);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户详细信息
     */
    @Select("select id, username, password, nickname, school, studentId, email, picture, signature, identity, point from user where username = #{username} limit 1")
    User queryUserByUsername(String username);

    /**
     * 用户注册请求
     *
     * @param user 用户信息
     * @return 注册结果
     */
    @Insert("insert into user (username,password,studentId,school,email) values (#{username},#{password},#{studentId},#{school},#{email})")
    int registration(User user);

    /**
     * 根据学号和邮箱找回密码
     *
     * @param email     邮箱
     * @param studentId 学号
     * @param username  用户名
     * @return 密码
     */
    @Select("select password from user where username = #{username} and email = #{email} and studentId = #{studentId}")
    String getPassword(String username, String email, int studentId);

    /**
     * 获取所有的用户列表
     *
     * @return 用户列表
     */
    @Select("select id, username, nickname, studentId, signature from user where identity != 1 and identity != 2;")
    List<User> getAllUserOfNotAdmin();
}