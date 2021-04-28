package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.OverflowListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OverflowListGoodsDao {

    void insertOverflowListGoods(List<OverflowListGoods> list, @Param("overflowListId") Integer overflowListId);

    List<OverflowListGoods> selectGoodsList(Integer overflowListId);
}
