package com.atguigu.jxc.service;

public interface GoodsTypeService {


    //查询商品所有分类
    String loadGoodsType();

    //新增分类
    void save(String goodsTypeName, Integer pId);

    //删除分类
    void delete(Integer goodsTypeId);

}
