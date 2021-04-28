package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.OverflowList;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface OverflowListGoodsService {

    //新增报溢单
    void save(OverflowList overflowList, String overflowListGoodsStr, HttpSession session);

    //报溢单商品信息
    Map<String, Object> getGoodsList(Integer overflowListId);
}
