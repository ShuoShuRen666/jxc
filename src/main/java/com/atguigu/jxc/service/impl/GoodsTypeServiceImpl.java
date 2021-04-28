package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.entity.GoodsType;
import com.atguigu.jxc.service.GoodsTypeService;
import com.atguigu.jxc.vo.GoodsTypeVO;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeDao goodsTypeDao;

    //查询商品所有分类
    @Override
    public String loadGoodsType() {
        List<GoodsTypeVO> goodsTypeVOList = new ArrayList<>();

        List<GoodsType> goodsTypeList = goodsTypeDao.loadGoodsType();
        for (GoodsType goodsType : goodsTypeList) {

                GoodsTypeVO goodsTypeVO = new GoodsTypeVO();
                Map<String, Object> map = new HashMap<>();
                map.put("state",goodsType.getGoodsTypeState());

                goodsTypeVO.setId(goodsType.getGoodsTypeId());
                goodsTypeVO.setText(goodsType.getGoodsTypeName());
                goodsTypeVO.setState(goodsType.getGoodsTypeState() == 1 ? "closed" : "open");
                goodsTypeVO.setAttributes(map);
                goodsTypeVO.setPId(goodsType.getPId());

                goodsTypeVOList.add(goodsTypeVO);
        }
        //map<pId, List<GoodsTypeVO>>
        Map<Integer, List<GoodsTypeVO>> collect = goodsTypeVOList.stream().collect(Collectors.groupingBy(GoodsTypeVO::getPId));

        for (GoodsTypeVO goodsTypeVO : goodsTypeVOList) {
            if(goodsTypeVO.getAttributes().get("state").equals(1)){
                goodsTypeVO.setChildren(collect.get(goodsTypeVO.getId()));
            }
        }

        List<GoodsTypeVO> goodsTypeVOList1 = new ArrayList<>();
        Gson gson = new Gson();
        for (GoodsTypeVO goodsTypeVO : goodsTypeVOList) {
            if(goodsTypeVO.getPId() == -1){
                goodsTypeVOList1.add(goodsTypeVO);
                break;
            }
        }
        return gson.toJson(goodsTypeVOList1);
    }

    //新增分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String goodsTypeName, Integer pId) {

        goodsTypeDao.save(goodsTypeName,pId);

        //修改对应pId的 goodsTypeState
        goodsTypeDao.updateByState1PId(pId);
    }

    //删除分类
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer goodsTypeId) {



        //如果没有分类是这个要删除分类的父类的子类，那么把他的父类的goods_type_state设置为0
        //先获取 goodsTypeId 对应的父分类
        GoodsType GoodsType = goodsTypeDao.selectById(goodsTypeId);
        Integer pId = GoodsType.getPId();
        GoodsType ParentGoodsType = goodsTypeDao.selectById(pId);
        Integer parentGoodsTypeId = ParentGoodsType.getGoodsTypeId();

        //删除数据
        goodsTypeDao.delete(goodsTypeId);

        //获取全部分类信息
        List<GoodsType> goodsTypeList = goodsTypeDao.loadGoodsType();
        int count = 0;
        for (GoodsType goodsType : goodsTypeList) {
            //如果其他分类的父id 与 父分类的id相等 说明还有子分类
            if(goodsType.getPId().equals(parentGoodsTypeId)){
                //记录
                count++;
            }
        }
        if(count == 0){
            //说明没有子分类,将goods_type_state 设置为0
            goodsTypeDao.updateByState0Id(parentGoodsTypeId);
        }


    }
}
