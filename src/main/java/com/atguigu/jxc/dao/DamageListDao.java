package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListDao {

    //保存到数据库,返回主键id
    Integer insertDamageList(DamageList damageListNew);

    //报损单查询
    List<DamageList> selectListByTime(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
