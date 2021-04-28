package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 商品类别
 */
public interface GoodsTypeDao {



    Integer updateGoodsTypeState(GoodsType parentGoodsType);


    //获取商品类别
    List<GoodsType> loadGoodsType();

    //新增分类
    void save(@Param("goodsTypeName") String goodsTypeName, @Param("pId") Integer pId);

    //修改对应pId的 goodsTypeState 为1
    void updateByState1PId(Integer pId);

    //删除分类
    void delete(Integer goodsTypeId);

    //根据id获取分类信息
    GoodsType selectById(Integer goodsTypeId);

    //修改对应Id的 goodsTypeState 为0
    void updateByState0Id(Integer goodsTypeId);
}
