package com.atguigu.jxc.service;

import java.util.Map;

public interface OverflowListService {

    //报溢单查询
    Map<String, Object> list(String sTime, String eTime);
}
