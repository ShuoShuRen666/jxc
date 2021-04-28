package com.atguigu.jxc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 商品类别
 */
@Data
@NoArgsConstructor
public class GoodsType {

  private Integer goodsTypeId;
  private String goodsTypeName;
  private Integer pId;
  private Integer goodsTypeState;  //0表示叶子节点

  public GoodsType(String goodsTypeName, Integer goodsTypeState, Integer pId) {
    this.goodsTypeName = goodsTypeName;
    this.goodsTypeState = goodsTypeState;
    this.pId = pId;
  }
}
