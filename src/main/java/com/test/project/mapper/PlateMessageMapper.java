package com.test.project.mapper;

import com.test.project.entity.PlateMessage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlateMessageMapper {
    /**
     * 删除记录
     *
     * @param id id
     * @return 删除结果
     */
    @Delete("delete from plateMessage where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录
     * @return 添加结果
     */
    @Insert("insert into plateMessage(title, content, plateId, block, userId, value) values "
            + "(#{title}, #{content}, #{plateId}, #{block}, #{userId}, #{value});")
    int insert(PlateMessage record);

    /**
     * 根据主键id查询记录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Select("select id, title, content, time, plateId, instructions, replies, priority, block, userId, value from plateMessage where id = #{id};")
    PlateMessage selectByPrimaryKey(Integer id);

    /**
     * 更新板块消息内容
     *
     * @param record 新的记录
     * @return 更新结果
     */
    @Update("update plateMessage set title = #{title}, content = #{content}, plateId = #{plateId}, block = #{block}, userId = #{userId}, value  = #{value} where id = #{id}")
    int updateByPrimaryKeySelective(PlateMessage record);


    /**
     * 增加访问量
     *
     * @param id 板块消息id
     * @return 增加结果
     */
    @Update("update plateMessage set instructions = instructions + 1 where id = #{id}")
    int addInstructions(int id);

    /**
     * 增加消息回复量
     *
     * @param id 板块消息id
     * @return 增加结果
     */
    @Update("update plate set replies = replies + 1 where id = #{id}")
    int addReplies(int id);

    /**
     * 修改板块消息的优先级
     *
     * @param id 板块消息id
     * @return 增加结果
     */
    @Update("update plate set priority = #{priority} where id = #{id}")
    int modifyPriority(int id, int priority);

    /**
     * 修改板块消息的优先级
     *
     * @param id 板块消息id
     * @return 增加结果
     */
    @Update("update plate set block = #{block} where id = #{id}")
    int modifyBlock(int id, int block);

    /**
     * 根据板块id查询数据
     * @param plateId 板块id
     * @return 数据列表
     */
    @Select("select id, title, content, time, plateId, instructions, replies, priority, block, userId, value from plateMessage where plateId = #{plateId} order by time")
    List<PlateMessage> findAllByPlateId(int plateId);
}