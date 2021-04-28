package com.atguigu.jxc.controller;

import com.atguigu.jxc.entity.Unit;
import com.atguigu.jxc.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("unit")
public class UnitController {

    @Autowired
    private UnitService unitService;

    //查询所有商品单位
    @PostMapping("list")
    public Map<String,Object> getUnitList(){
        return unitService.getUnitList();
    }
}
