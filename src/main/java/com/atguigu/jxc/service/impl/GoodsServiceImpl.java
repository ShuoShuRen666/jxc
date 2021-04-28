package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.entity.Log;
import com.atguigu.jxc.service.CustomerReturnListGoodsService;
import com.atguigu.jxc.service.GoodsService;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsDao goodsDao;

    @Override
    public ServiceVO getCode() {

        // 获取当前商品最大编码
        String code = goodsDao.getMaxCode();

        // 在现有编码上加1
        Integer intCode = Integer.parseInt(code) + 1;

        // 将编码重新格式化为4位数字符串形式
        String unitCode = intCode.toString();

        for(int i = 4;i > intCode.toString().length();i--){

            unitCode = "0"+unitCode;

        }
        return new ServiceVO<>(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS, unitCode);
    }

    //分页查询商品库存信息（首页）
    @Override
    public Map<String, Object> selectListInventoryByPage(Integer page, Integer rows, String codeOrName, Integer goodsTypeId) {
        Map<String,Object> map = new HashMap<>();
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;

        List<Goods> goodsList = goodsDao.selectListInventoryByPage(offSet,rows,codeOrName,goodsTypeId);

        map.put("rows",goodsList);
        map.put("total",goodsDao.getGoodsInventoryCount(codeOrName, goodsTypeId));
        return map;
    }

    //添加或修改商品信息
    @Override
    public void saveOrUpdate(Goods goods) {
        if(goods.getGoodsId() == null){
            //添加
            goodsDao.insert(goods);
        }else {
            //修改
            goodsDao.update(goods);
        }
    }

    //删除商品信息(要判断商品状态,入库、有进货和销售单据的不能删除)
    @Override
    public void remove(Integer goodsId) {
        Integer state = goodsDao.selectGoodsState(goodsId);
        if(state == 0){
            goodsDao.delete(goodsId);
        }
    }

    //分页查询无库存商品信息
    @Override
    public Map<String, Object> getNoInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = goodsDao.selectNoInventoryQuantity(offSet,rows,nameOrCode);

        map.put("total",goodsDao.getGoodsInventoryCountByCodeOrName(nameOrCode));
        map.put("rows",goodsList);
        return map;
    }

    //分页查询有库存商品信息
    @Override
    public Map<String, Object> getHasInventoryQuantity(Integer page, Integer rows, String nameOrCode) {
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = goodsDao.getHasInventoryQuantity(offSet,rows,nameOrCode);

        map.put("total",goodsDao.getGoodsInventoryCountByCodeOrNameHasStock(nameOrCode));
        map.put("rows",goodsList);
        return map;
    }

    //添加商品期初库存   修改数量或成本价
    @Override
    public void saveStock(Integer goodsId, Integer inventoryQuantity, double purchasingPrice) {
        goodsDao.updateStock(goodsId,inventoryQuantity,purchasingPrice);
    }

    //删除商品库存
    @Override
    public void deleteStock(Integer goodsId) {
        Integer state = goodsDao.selectGoodsState(goodsId);
        if(state == 0){
            goodsDao.deleteStock(goodsId);
        }

    }

    //查询所有 当前库存量 小于 库存下限的商品信息
    @Override
    public Map<String, Object> getListAlarm() {
        Map<String, Object> map = new HashMap<>();
        List<Goods> goodsList = goodsDao.selectListAlarm();
        map.put("rows",goodsList);
        return map;
    }


}
