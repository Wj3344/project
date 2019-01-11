package com.test.project.mapper;

import com.test.project.entity.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface MessageMapper {

    /**
     * 删除记录
     *
     * @param id 记录id
     * @return 删除结果
     */
    @Delete("delete from `message` where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录内容
     * @return 添加结果
     */
    @Insert("insert into message(author, receiver, message, plateMessageId) values (#{author}, #{receiver}, #{message}, #{plateMessageId});")
    int insert(Message record);

    /**
     * 根据主键id查询记录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Select("select id, author, receiver, message, time, plateMessageId from message where id = #{id}")
    Message selectByPrimaryKey(Integer id);

    /**
     * 更新一条消息 （主要是留言内容）
     *
     * @param record 新的记录
     * @return 修改结果
     */
    @Update("update message set message = #{message} where id = #{id};")
    int updateByPrimaryKey(Message record);
}