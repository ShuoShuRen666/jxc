package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.atguigu.jxc.service.OverflowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("overflowListGoods")
public class overflowListGoodsController {

    @Autowired
    private OverflowListGoodsService overflowListGoodsService;

    @Autowired
    private OverflowListService overflowListService;

    //新增报溢单
    @PostMapping("save")
    public ServiceVO save(OverflowList overflowList, String overflowListGoodsStr, HttpSession session){
        overflowListGoodsService.save(overflowList,overflowListGoodsStr,session);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    //报溢单查询
    @PostMapping("list")
    public Map<String,Object> list(String sTime, String eTime){
        return overflowListService.list(sTime,eTime);
    }

    //报溢单商品信息
    @PostMapping("goodsList")
    public Map<String,Object> goodsList(Integer overflowListId){
        return overflowListGoodsService.getGoodsList(overflowListId);
    }
}
