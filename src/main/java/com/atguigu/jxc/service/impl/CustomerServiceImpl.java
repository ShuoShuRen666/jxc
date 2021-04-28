package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.CustomerDao;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    //客户列表分页
    @Override
    public Map<String, Object> pageList(Integer page, Integer rows, String customerName) {
        Map<String, Object> map = new HashMap<>();
        page = page == 0 ? 1 : page;
        int offSet = (page - 1) * rows;
        List<Customer> customerList = customerDao.pageList(offSet,rows,customerName);
        Integer count = customerDao.getCustomerCount(customerName);
        map.put("rows",customerList);
        map.put("total",count);
        return map;
    }

    //客户添加或修改
    @Override
    public Integer saveOrUpdate(Customer customer) {
        if(customer.getCustomerId() == null){
            return customerDao.save(customer);
        }else {
            return customerDao.updateById(customer);
        }

    }

    //客户删除
    @Override
    public Integer deleteByIds(String ids) {
        String[] idArr = ids.split(",");
        Integer count = customerDao.deleteByIds(idArr);
        return count;
    }
}
