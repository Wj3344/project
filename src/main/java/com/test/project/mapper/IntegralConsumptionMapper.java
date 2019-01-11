package com.test.project.mapper;

import com.test.project.entity.IntegralConsumption;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 积分消费的数据映射
 */
@Component
@Mapper
public interface IntegralConsumptionMapper {
    /**
     * 删除一条积分消费记录
     *
     * @param id 记录id
     * @return 删除记录
     */
    @Delete("delete from integralConsumption where id = #{id}")
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加一条积分消费记录
     *
     * @param record 消费记录
     * @return 添加结果
     */
    @Insert("insert into  integralConsumption (consumer, beneficiary, value) values (#{consumer}, #{beneficiary}, #{value});")
    int insert(IntegralConsumption record);


    /**
     * 根据id查询一条消费记录
     *
     * @param id id
     * @return 记录
     */
    @Select("select id, consumer, beneficiary, value, time from integralConsumption where id = #{id};")
    IntegralConsumption selectById(Integer id);

    /**
     * 根据生产者查询消费记录
     *
     * @param consumer 花钱的人的id
     * @return 记录列表
     */
    @Select("select id, consumer, beneficiary, value, time from integralConsumption where consumer = #{consumer};")
    List<IntegralConsumption> findByConsumer(int consumer);

    /**
     * 根据受益者查询消费记录
     *
     * @param beneficiary 收钱的人的id
     * @return 记录列表
     */
    @Select("select id, consumer, beneficiary, value, time from integralConsumption where beneficiary = #{beneficiary};")
    List<IntegralConsumption> findByBeneficiary(int beneficiary);


    /**
     * 修改一条积分消费记录
     *
     * @param record 新的记录
     * @return 修改结果
     */
    @Update("update integralConsumption set value = #{value} where id = #{id}")
    int updateByPrimaryKey(IntegralConsumption record);
}