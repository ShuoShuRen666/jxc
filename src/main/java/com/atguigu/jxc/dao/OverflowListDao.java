package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowListDao {

    Integer insertOverflowList(OverflowList overflowList);

    //报溢单查询
    List<OverflowList> selectList(@Param("sTime") String sTime, @Param("eTime") String eTime);
}
