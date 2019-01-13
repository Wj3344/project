package com.test.project.mapper;

import com.test.project.entity.Plate;
import com.test.project.entity.PlateMessage;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper
public interface PlateMapper {

    /**
     * 根据主键id删除一条记录
     *
     * @param id 主键id
     * @return 删除结果
     */
    @Delete("delete from  plate where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条记录
     *
     * @param record 记录
     * @return 添加结果
     */
    @Insert("insert into plate (name, referral, admin) values (#{name}, #{referral}, #{admin});")
    int insert(Plate record);

    /**
     * 根据id查询板块信息
     *
     * @param id 搬开id
     * @return 查询结果
     */
    @Select("select id, name, referral, admin from plate where id = #{id};")
    Plate selectByPrimaryKey(Integer id);

    /**
     * 更新记录
     *
     * @param record 新的记录
     * @return 修改结果
     */
    @Update("update plate set name = #{admin}, referral = #{referral}, admin = #{admin} where id = #{id}")
    int updateByPrimaryKey(Plate record);

    /**
     * 根据管理员id查询时候有其负责的板块
     *
     * @param admin Admin
     * @return 查询结果
     */
    @Select("select id from plate where admin= #{admin}")
    List<Integer> findByAdmin(int admin);

    /**
     * 获取一个板块下的一部分内容
     *
     * @param plateId 板块id
     * @param number  数量
     * @return 查询结果
     */
    @Select("select id, title,content,time from plateMessage where id = #{plateId} order by time limit #{number}")
    List<PlateMessage> getByIdAndNumber(int plateId, int number);

    /**
     * 获取所有的板块
     *
     * @return 板块列表
     */
    @Select("select id, name, referral, admin from plate;")
    List<Plate> getAllPlate();
}