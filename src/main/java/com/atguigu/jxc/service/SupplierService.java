package com.atguigu.jxc.service;

import com.atguigu.jxc.entity.Supplier;

import java.util.Map;

public interface SupplierService {

    //分页查询供应商
    Map<String, Object> getPageList(Integer page, Integer rows, String supplierName);

    //添加或修改供应商
    void save(Supplier supplier);

    //删除供应商
    void delete(String ids);
}
