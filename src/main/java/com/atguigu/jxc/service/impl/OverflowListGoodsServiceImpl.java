package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.dao.OverflowListGoodsDao;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.entity.OverflowListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.OverflowListGoodsService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OverflowListGoodsServiceImpl implements OverflowListGoodsService {

    @Autowired
    private OverflowListDao overflowListDao;

    @Autowired
    private OverflowListGoodsDao overflowListGoodsDao;

    //新增报溢单
    @Override
    public void save(OverflowList overflowList, String overflowListGoodsStr, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
        overflowList.setUserId(user.getUserId());
        overflowList.setTrueName(user.getTrueName());
        //插入数据库 并返回主键
        overflowListDao.insertOverflowList(overflowList);

        Gson gson = new Gson();
        List<OverflowListGoods> list = gson.fromJson(overflowListGoodsStr, List.class);

        //插入数据库
        overflowListGoodsDao.insertOverflowListGoods(list,overflowList.getOverflowListId());
    }

    //报溢单商品信息
    @Override
    public Map<String, Object> getGoodsList(Integer overflowListId) {
        Map<String, Object> map = new HashMap<>();
        List<OverflowListGoods> overflowListGoodsList = overflowListGoodsDao.selectGoodsList(overflowListId);
        map.put("rows",overflowListGoodsList);
        return map;
    }
}
