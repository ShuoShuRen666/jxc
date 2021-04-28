package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierDao {
    //分页查询供应商
    List<Supplier> getPageList(@Param("page") Integer page, @Param("rows") Integer rows, @Param("supplierName") String supplierName);

    //根据供应商姓名查询供应商数量
    Integer getSupplierCount(String supplierName);

    //添加供应商
    void save(Supplier supplier);

    //修改供应商
    void updateById(Supplier supplier);

    //删除供应商
    void deleteByIds(@Param("idArr") String[] idArr);
}
