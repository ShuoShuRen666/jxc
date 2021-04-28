package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.DamageListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DamageListGoodsDao {

    //保存报损明细
    void insertDamageListGoods( List<DamageListGoods> damageListGoodsList, @Param("damageListId") Integer damageListId);

    //查询报损单商品信息
    List<DamageListGoods> selectGoodsList(Integer damageListId);


}
