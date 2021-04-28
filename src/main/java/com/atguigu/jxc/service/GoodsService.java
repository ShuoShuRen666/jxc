package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Goods;

import java.util.Map;

public interface GoodsService {

    ServiceVO getCode();

    //分页查询商品库存信息（首页）
    Map<String, Object> selectListInventoryByPage(Integer page, Integer rows, String codeOrName, Integer goodsTypeId);

    //添加或修改商品信息
    void saveOrUpdate(Goods goods);

    //删除商品信息(要判断商品状态,入库、有进货和销售单据的不能删除)
    void remove(Integer goodsId);

    //分页查询无库存商品信息
    Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    //分页查询有库存商品信息
    Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode);

    //添加商品期初库存   修改数量或成本价
    void saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice);

    //删除商品库存
    void deleteStock(Integer goodsId);

    //查询所有 当前库存量 小于 库存下限的商品信息
    Map<String, Object> getListAlarm();
}
