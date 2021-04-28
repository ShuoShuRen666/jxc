package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.OverflowListDao;
import com.atguigu.jxc.entity.OverflowList;
import com.atguigu.jxc.service.OverflowListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OverflowListServiceImpl implements OverflowListService {

    @Autowired
    private OverflowListDao overflowListDao;

    //报溢单查询
    @Override
    public Map<String, Object> list(String sTime, String eTime) {
        Map<String, Object> map = new HashMap<>();
        List<OverflowList> overflowListList = overflowListDao.selectList(sTime,eTime);
        map.put("rows",overflowListList);
        return map;
    }
}
