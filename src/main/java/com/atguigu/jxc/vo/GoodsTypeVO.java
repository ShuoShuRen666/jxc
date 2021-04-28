package com.atguigu.jxc.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GoodsTypeVO {
    //goods_type_id
    Integer id;

    Integer pId;

    //goods_type_name
    String text;

    //state为1 值就是closed  state为0 值就是open
    String state;

    String iconCls = "goods-type";

    /*
        "id": 1,
        "text": "所有类别",
        "state": "closed",
        "iconCls": "goods-type",
        "attributes": {
            "state": 1
        },
        "children": []
     */
    Map<String, Object> attributes;

    /*
    {
                "id": 2,
                "text": "服饰",
                "state": "closed",
                "iconCls": "goods-type",
                "attributes": {
                    "state": 1
                },
                "children": [
                    {
                        "id": 6,
                        "text": "连衣裙",
                        "state": "open",
                        "iconCls": "goods-type",
                        "attributes": {
                            "state": 0
                        }
                    },
     */
    List<GoodsTypeVO> children;

}
