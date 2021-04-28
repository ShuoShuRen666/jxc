package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ErrorCode;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.Customer;
import com.atguigu.jxc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 客户列表分页
     * @param page 当前页
     * @param rows 每页显示数量
     * @param customerName 客户名称
     * @return
     */
    @PostMapping("list")
    public Map<String,Object> list(Integer page, Integer rows, String  customerName){
        return customerService.pageList(page,rows,customerName);
    }

    //客户添加或修改
    @PostMapping("save")
    public ServiceVO saveOrUpdate(Customer customer){
        Integer count = customerService.saveOrUpdate(customer);
        if(count > 0){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }else
            return new ServiceVO(ErrorCode.REQ_ERROR_CODE,ErrorCode.REQ_METHOD_ERR_MESS);
    }

    @PostMapping("delete")
    public ServiceVO deleteByIds(String ids){
        Integer count = customerService.deleteByIds(ids);
        if(count > 0){
            return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
        }else
            return new ServiceVO(ErrorCode.REQ_ERROR_CODE,ErrorCode.REQ_METHOD_ERR_MESS);
    }
}
