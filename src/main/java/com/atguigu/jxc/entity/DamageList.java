package com.atguigu.jxc.entity;

import lombok.Data;
/**
 * 报损
 */
@Data
public class DamageList {

  private Integer damageListId;
  private String damageNumber; //报损单号
  private String damageDate;
  private String remarks;
  private Integer userId;

  private String trueName;

}
