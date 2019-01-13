package com.test.project.mapper;

import com.test.project.entity.PrivateLetter;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PrivateLetterMapper {
    /**
     * 删除记录
     *
     * @param id 记录id
     * @return 删除结果
     */
    @Delete("delete from privateLetter where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录
     * @return 添加结果
     */
    @Insert("insert into privateLetter (author, receiver, message) values (#{author},#{receiver},#{message})")
    int insert(PrivateLetter record);

    /**
     * 根据主键id查询记录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Select("select id, author, receiver, message, time, sign from privateLetter where id = #{id};")
    PrivateLetter selectByPrimaryKey(Integer id);


    /**
     * 更新记录（更新消息状态）
     * @param record 新的消息
     * @return 更新结果
     */
    @Update("update privateLetter set sign = #{sign} where id = #{id};")
    int updateSign(PrivateLetter record);

    /**
     * 查询该消息是否被阅读
     *
     * @param id 消息id
     * @return 查询结果
     */
    @Select("select sign from privateLetter where id = #{id};")
    boolean getMessageSign(Integer id);

    /**
     * 查询用户所有未读消息
     *
     * @param receiver 收件人id
     * @return 查询结果
     */
    @Select("select id, author, receiver, message, time, sign from privateLetter where receiver = #{receiver};")
    List<PrivateLetter> findAllMessage(int receiver);
}