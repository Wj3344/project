package com.test.project.mapper;

import com.test.project.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserPermissionMapper {

    /**
     * 查询用户权限
     *
     * @param userName 用户名
     * @return 查询结果
     */
    @Select("select * " +
            "from `permission` " +
            "where `permission`.`id` in ( " +
            "  select `role_permission`.`permissionId`\n" +
            "  from `role_permission`\n" +
            "  where `role_permission`.`identity` in (\n" +
            "    select `user`.`identity` from `user` where `user`.username = #{username}))")
    @Results(id = "permission", value = {
            @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(property = "url", column = "url", javaType = String.class),
            @Result(property = "name", column = "name", javaType = String.class)
    })
    List<Permission> findByUserName(String userName);
}
