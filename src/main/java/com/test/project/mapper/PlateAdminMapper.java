package com.test.project.mapper;

import com.test.project.entity.PlateAdmin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface PlateAdminMapper {
    /**
     * 根据主键删除一条记录
     *
     * @param id 主键id
     * @return 删除结果
     */
    @Delete("delete from plateAdmin where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录
     * @return 添加结果
     */
    @Insert("insert into plateAdmin(plateId, userId) values (#{plateId}, #{userId});")
    int insert(PlateAdmin record);

    /**
     * 根据主键id查询介入
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Select("select id, plateId, userId from plateAdmin where id = #{id};")
    PlateAdmin selectByPrimaryKey(Integer id);


    /**
     * 更新记录
     *
     * @param record 新的记录
     * @return 更新结果
     */
    @Update("update plateAdmin set plateId = #{plateId},userId = #{userId} where id = #{id}")
    int updateByPrimaryKey(PlateAdmin record);

    /**
     * 根据板块id查询
     *
     * @param plateId 板块id
     * @return 查询结果
     */
    @Select("select id, plateId, userId from plateAdmin where plateId = #{plateId};")
    List<PlateAdmin> findByPlateId(int plateId);

    /**
     * 根据板块负责人id查找
     *
     * @param adminId 板块负责人id
     * @return 查询结果
     */
    @Select("select id, plateId, userId from plateAdmin where userId = #{adminId};")
    List<PlateAdmin> findByAdminId(int adminId);
    /**
     * 删除一条记录
     * @param plateId 板块id
     * @param userId 用户id
     * @return 删除结果
     */
    @Delete("delete from plateAdmin where plateId = #{plateId} and userId = #{userId}")
    int deleteByUserIdAndPlateId(int plateId, int userId);

    /**
     * 删除板块负责人
     * @param id 板块id
     * @return 删除结果
     */
    @Delete("delete from plateAdmin where plateId = #{id}")
    int deleteByPLateId(int id);
}