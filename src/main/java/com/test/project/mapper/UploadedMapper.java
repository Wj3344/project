package com.test.project.mapper;

import com.test.project.entity.Uploaded;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UploadedMapper {

    /**
     * 删除一条记录
     *
     * @param id 记录id
     * @return 删除结果
     */
    @Delete("delete  from uploaded where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录
     * @return 添加结果
     */
    @Insert(value = "insert into uploaded(title, content, time, plateId, userId, value, status) values "
            + "  (#{title}, #{content}, #{time}, #{plateId}, #{userId}, #{value}, #{status});")
    int insert(Uploaded record);

    /**
     * 根据主键id查询一条记录
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Select("select id, title, content, time, plateId, userId, value, status from uploaded where id = #{id};")
    Uploaded selectByPrimaryKey(Integer id);

    /**
     * 更新一条记录
     *
     * @param record 新的记录
     * @return 更新结果
     */
    @Update("update `uploaded` set `title` = #{title}, `content` = #{content}, `plateId` = #{plateId}, "
            + "`userId` = #{userId}, `value` = #{value} where `id` = #{id};")
    int updateByPrimaryKeySelective(Uploaded record);

    /**
     * 更新消息状态
     *
     * @param record 状态和消息id
     * @return 更新结果
     */
    @Update("update `uploaded` set status = #{status} where id = #{id};")
    int updateMessageStatus(Uploaded record);

    /**
     * 根据板块id获取所有上传的消息
     *
     * @param plateId 板块id
     * @return 查询结果
     */
    @Select("select id, title, content, time, plateId, userId, value, status from uploaded where plateId = #{plateId}")
    List<Uploaded> findAllByPlateIdOrOrderByTime(int plateId);
}