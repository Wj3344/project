package com.test.project.mapper;

import com.test.project.entity.Identity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IdentityMapper {

    /**
     * 根据主键删除一条记录
     *
     * @param id 主键id
     * @return 删除结果
     */
    @Delete("delete from identity where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录
     * @return 添加结果
     */
    @Insert("insert into identity (description) values (#{description})")
    int insert(Identity record);

    /**
     * 根据主键查询信息
     *
     * @param id 主键id
     * @return 查询结果
     */
    @Select("select id, description from identity where id = #{id};")
    Identity selectByPrimaryKey(Integer id);

    @Select("select id, description from identity")
    @Results(id = "identity", value = {
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "description", column = "description", javaType = String.class),
    })
    List<Identity> getAll();

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 更新结果
     */
    @Update("update identity set description = #{description} where id = #{id}")
    int updateByPrimaryKey(Identity record);

    /**
     * 通过用户名查找用户角色
     *
     * @param username 用户名
     * @return 查询结果
     */
    @Select("select id, description from identity where id = (select identity from user where username = #{username})")
    List<Identity> findByUsername(String username);
}