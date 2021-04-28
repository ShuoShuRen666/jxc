package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Goods;
import com.atguigu.jxc.service.GoodsService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description 商品信息Controller
 */
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询商品库存信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param codeOrName 商品编码或名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("/listInventory")
    @RequiresPermissions(value = "当前库存查询")
    public Map<String,Object> listInventory( Integer page,
                                             Integer rows,
                                             String codeOrName,
                                             Integer goodsTypeId){
        return goodsService.selectListInventoryByPage(page,rows,codeOrName,goodsTypeId);
    }


    /**
     * 分页查询商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param goodsName 商品名称
     * @param goodsTypeId 商品类别ID
     * @return
     */
    @PostMapping("/list")
    public Map<String,Object> gatGoodsListByPage(Integer page, Integer rows, String goodsName, Integer goodsTypeId){
        return goodsService.selectListInventoryByPage(page,rows,goodsName,goodsTypeId);
    }


    /**
     * 生成商品编码
     * @return
     */
    @RequestMapping("/getCode")
    @RequiresPermissions(value = "商品管理")
    public ServiceVO getCode() {
        return goodsService.getCode();
    }

    /**
     * 添加或修改商品信息
     * @param goods 商品信息实体
     * @return
     */
    @PostMapping("save")
    public ServiceVO save(Goods goods){
        goodsService.saveOrUpdate(goods);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除商品信息(要判断商品状态,入库、有进货和销售单据的不能删除)
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("delete")
    public ServiceVO delete(Integer goodsId){
        goodsService.remove(goodsId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 分页查询无库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("getNoInventoryQuantity")
    public Map<String,Object> getNoInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        return goodsService.getNoInventoryQuantity(page,rows,nameOrCode);
    }


    /**
     * 分页查询有库存商品信息
     * @param page 当前页
     * @param rows 每页显示条数
     * @param nameOrCode 商品名称或商品编码
     * @return
     */
    @PostMapping("getHasInventoryQuantity")
    public Map<String,Object> getHasInventoryQuantity(Integer page,Integer rows,String nameOrCode){
        return goodsService.getHasInventoryQuantity(page,rows,nameOrCode);
    }


    /**
     * 添加商品期初库存   修改数量或成本价
     * @param goodsId 商品ID
     * @param inventoryQuantity 库存
     * @param purchasingPrice 成本价
     * @return
     */
    @PostMapping("saveStock")
    public ServiceVO saveStock(Integer goodsId,Integer inventoryQuantity,double purchasingPrice){
        goodsService.saveStock(goodsId,inventoryQuantity,purchasingPrice);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 删除商品库存
     * @param goodsId 商品ID
     * @return
     */
    @PostMapping("deleteStock")
    public ServiceVO deleteStock(Integer goodsId){
        goodsService.deleteStock(goodsId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    /**
     * 查询库存报警商品信息
     * 查询所有 当前库存量 小于 库存下限的商品信息
     * @return
     */
    @PostMapping("listAlarm")
    public Map<String,Object> listAlarm(){
        return goodsService.getListAlarm();
    }

}
