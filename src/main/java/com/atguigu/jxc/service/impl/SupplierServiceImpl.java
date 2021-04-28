package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.SupplierDao;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    private SupplierDao supplierDao;


    //分页查询供应商
    @Override
    public Map<String, Object> getPageList(Integer page, Integer rows, String supplierName) {
        Map<String, Object> map = new HashMap<>();
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        List<Supplier> supplierList = supplierDao.getPageList(offSet,rows,supplierName);
        Integer count = supplierDao.getSupplierCount(supplierName);
        map.put("total",count);
        map.put("rows",supplierList);
        return map;
    }

    //添加或修改供应商
    @Override
    public void save(Supplier supplier) {
        if (supplier != null) {
            if (supplier.getSupplierId() != null) {
                supplierDao.updateById(supplier);
            } else {
                supplierDao.save(supplier);
            }
        }
    }

    //删除供应商
    @Override
    public void delete(String ids) {

        String[] idArr = ids.split(",");
        supplierDao.deleteByIds(idArr);
    }
}
