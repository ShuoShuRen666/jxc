package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.DamageListDao;
import com.atguigu.jxc.dao.DamageListGoodsDao;
import com.atguigu.jxc.entity.DamageList;
import com.atguigu.jxc.entity.DamageListGoods;
import com.atguigu.jxc.entity.User;
import com.atguigu.jxc.service.DamageListService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DamageListServiceImpl implements DamageListService {

    @Autowired
    private DamageListDao damageListDao;

    @Autowired
    private DamageListGoodsDao damageListGoodsDao;

    //保存报损单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDamage(DamageList damageList, String damageListGoodsStr, HttpSession session) {
        User user = (User) session.getAttribute("currentUser");
//        DamageListGoods damageListGoods = new DamageListGoods();
        DamageList damageListNew = new DamageList();

        damageListNew.setRemarks(damageList.getRemarks());
        damageListNew.setDamageDate(damageList.getDamageDate());
        damageListNew.setDamageNumber(damageList.getDamageNumber());//设置报损单号
        damageListNew.setUserId(user.getUserId());
        damageListNew.setTrueName(user.getTrueName());
        //保存到数据库,并返回主键id（自动注入到damageListNew）
        damageListDao.insertDamageList(damageListNew);

        Gson gson = new Gson();
        List<DamageListGoods> damageListGoodsList = gson.fromJson(damageListGoodsStr, List.class);
        //插入数据库
        damageListGoodsDao.insertDamageListGoods(damageListGoodsList,damageListNew.getDamageListId());
        /*for (DamageListGoods damageListGoods : damageListGoodsList) {
            //需要获取到报损总单的主键
            damageListGoods.setDamageListId(damageListId);
            //插入数据库
            damageListGoodsDao.insertDamageListGoods(damageListGoods);
        }*/
    }

    //报损单查询
    @Override
    public Map<String, Object> getListByTime(String sTime, String eTime) {
        Map<String, Object> map = new HashMap<>();
        List<DamageList> damageListList = damageListDao.selectListByTime(sTime,eTime);
        map.put("rows",damageListList);
        return map;
    }

    //查询报损单商品信息
    @Override
    public Map<String, Object> getGoodsList(Integer damageListId) {
        Map<String, Object> map = new HashMap<>();
        List<DamageListGoods> damageListGoodsList = damageListGoodsDao.selectGoodsList(damageListId);
        map.put("rows",damageListGoodsList);
        return map;
    }
}
