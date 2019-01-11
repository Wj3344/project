package com.test.project.mapper;

import com.test.project.entity.Downloaded;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 已经查看过的板块消息记录
 */
@Mapper
@Component
public interface DownloadedMapper {

    /**
     * 删除一条记录
     *
     * @param id 记录id
     * @return 删除结果
     */
    @Delete("delete  from downloaded where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录内容
     * @return 添加结果
     */
    @Insert("insert into downloaded(userId, messageId) values (#{userId}, #{messageId});")
    int insert(Downloaded record);

    /**
     * 根据主键查询记录
     *
     * @param id 主键id
     * @return 查询记录
     */
    @Select("select id, userId, messageId, time from downloaded where id = #{id};")
    Downloaded selectByPrimaryKey(Integer id);

    /**
     * 根据消费者id和板块消息id查询记录
     *
     * @param userId    消费者id
     * @param messageId 板块消息id
     * @return 查询结果
     */
    @Select("select id, userId, messageId, time from downloaded where userId = #{userId} and messageId = #{messageId};")
    Downloaded selectByUserIdAndMessageId(int userId, int messageId);

    /**
     * 根据消费者的id查询查看记录
     *
     * @param userId 消费者id
     * @return 查询结果
     */
    @Select("select id, userId, messageId, time from downloaded where userId = #{userId};")
    List<Downloaded> findByUserID(int userId);

    /**
     * 更新记录
     *
     * @param id 新的记录id
     * @return 更新结果
     */
    @Update("update downloaded set time = current_timestamp where id = #{id};")
    int updateByPrimaryKey(int id);
}