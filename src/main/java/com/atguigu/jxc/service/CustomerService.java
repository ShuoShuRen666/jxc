package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Customer;

import java.util.Map;

public interface CustomerService {
    //客户列表分页
    Map<String, Object> pageList(Integer page, Integer rows, String customerName);

    //客户添加或修改
    Integer saveOrUpdate(Customer customer);

    //客户删除
    Integer deleteByIds(String ids);
}
