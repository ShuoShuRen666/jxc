package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.DamageList;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface DamageListService {

    //保存报损单
    void saveDamage(DamageList damageList, String damageListGoodsStr, HttpSession session);

    //报损单查询
    Map<String, Object> getListByTime(String sTime, String eTime);

    //查询报损单商品信息
    Map<String, Object> getGoodsList(Integer damageListId);
}
