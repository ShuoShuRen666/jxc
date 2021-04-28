package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    //查询商品所有分类
    @PostMapping("/loadGoodsType")
    public String loadGoodsType(){

        return goodsTypeService.loadGoodsType();
    }

    //新增分类
    @PostMapping("save")
    public ServiceVO save(String goodsTypeName,Integer pId){
        goodsTypeService.save(goodsTypeName,pId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    //删除分类
    @PostMapping("delete")
    public ServiceVO delete(Integer goodsTypeId){
        goodsTypeService.delete(goodsTypeId);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

}
