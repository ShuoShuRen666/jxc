package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @description 商品信息
 */
public interface GoodsDao {


    String getMaxCode();


    //分页查询商品库存信息（首页）
    List<Goods> selectListInventoryByPage(@Param("page") Integer page, @Param("rows") Integer rows, @Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    //根据商品名称or商品编码or商品类别id 查询商品库存数量
    Integer getGoodsInventoryCount(@Param("codeOrName") String codeOrName, @Param("goodsTypeId") Integer goodsTypeId);

    Integer getGoodsInventoryCountByCodeOrName(@Param("codeOrName") String codeOrName);

    //添加商品
    void insert(Goods goods);

    //修改商品
    void update(Goods goods);

    //删除商品信息(要判断商品状态,入库、有进货和销售单据的不能删除)
    void delete(Integer goodsId);

    //根据id查询商品状态
    Integer selectGoodsState(Integer goodsId);

    //分页查询无库存商品信息
    List<Goods> selectNoInventoryQuantity(@Param("page") Integer page, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    //分页查询有库存商品信息
    List<Goods> getHasInventoryQuantity(@Param("offSet") int offSet, @Param("rows") Integer rows, @Param("nameOrCode") String nameOrCode);

    //查询有库存的商品数量
    Integer getGoodsInventoryCountByCodeOrNameHasStock(@Param("nameOrCode") String nameOrCode);

    //添加商品期初库存   修改数量或成本价
    void updateStock(@Param("goodsId") Integer goodsId, @Param("inventoryQuantity") Integer inventoryQuantity, @Param("purchasingPrice") double purchasingPrice);

    //删除商品库存
    void deleteStock(Integer goodsId);

    //库存报警
    List<Goods> selectListAlarm();


}
