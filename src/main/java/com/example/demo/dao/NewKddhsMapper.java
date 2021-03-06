package com.example.demo.dao;

import com.example.demo.dao.model.NewKddhs;
import com.example.demo.dao.model.NewKddhsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewKddhsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    long countByExample(NewKddhsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int deleteByExample(NewKddhsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int insert(NewKddhs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int insertSelective(NewKddhs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    List<NewKddhs> selectByExample(NewKddhsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    NewKddhs selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int updateByExampleSelective(@Param("record") NewKddhs record, @Param("example") NewKddhsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int updateByExample(@Param("record") NewKddhs record, @Param("example") NewKddhsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int updateByPrimaryKeySelective(NewKddhs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table new_kddhs
     *
     * @mbg.generated Sat Mar 06 14:19:42 CST 2021
     */
    int updateByPrimaryKey(NewKddhs record);
}