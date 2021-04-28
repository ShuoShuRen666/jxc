package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.service.DamageListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("damageListGoods")
public class DamageListGoodsController {

    @Autowired
    private DamageListService damageListService;

    //保存报损单
    @PostMapping("save")
    public ServiceVO save(DamageList damageList, String damageListGoodsStr, HttpSession session){
        damageListService.saveDamage(damageList,damageListGoodsStr,session);
        return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
    }

    //报损单查询  开始时间 结束时间
    @PostMapping("list")
    public Map<String,Object> list(String sTime, String eTime){
        return damageListService.getListByTime(sTime,eTime);
    }

    //查询报损单商品信息
    @PostMapping("goodsList")
    public Map<String,Object> goodsList(Integer damageListId){
        return damageListService.getGoodsList(damageListId);
    }
}
