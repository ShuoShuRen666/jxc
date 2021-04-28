package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.Supplier;
import com.atguigu.jxc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("supplier")
public class supplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 分页查询供应商
     * @param page 当前页数
     * @param rows  每页显示记录数
     * @param supplierName 供应商名称
     * @return
     */
    @PostMapping("list")
    public Map<String,Object> pageList(Integer page, Integer rows, String supplierName){
        Map<String,Object> map = supplierService.getPageList(page,rows,supplierName);
        return map;
    }

    //添加或修改供应商
    @PostMapping("save")
    public ServiceVO save(Supplier supplier){
        supplierService.save(supplier);
        return new ServiceVO(100,"请求成功");
    }

    //删除供应商
    @PostMapping("delete")
    public ServiceVO delete(String ids){
        supplierService.delete(ids);
        return new ServiceVO(100,"请求成功");
    }


}
