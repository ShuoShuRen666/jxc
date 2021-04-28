package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Customer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerDao {
    //客户列表分页
    List<Customer> pageList(@Param("page") Integer page, @Param("rows") Integer rows, @Param("customerName") String customerName);

    //根据客户名称，获取客户数量
    Integer getCustomerCount(String customerName);

    //客户添加
    Integer save(Customer customer);

    //客户修改
    Integer updateById(Customer customer);

    //客户删除
    Integer deleteByIds(@Param("idArr") String[] idArr);
}
