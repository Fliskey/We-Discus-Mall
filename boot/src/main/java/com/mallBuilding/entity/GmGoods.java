package com.mallBuilding.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.baomidou.mybatisplus.annotation.TableField;
/**
 * 使用 @TableField(exist = false) ，表示该字段在数据库中不存在 ，所以不会插入数据库中
 * 使用 transient 、 static 修饰属性也不会插入数据库中
 */

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("gm_goods")
@Data
  @EqualsAndHashCode(callSuper = false)
    public class GmGoods implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId
    private Integer id;

    private Integer userId;

    private String name;

    private String type;

    private Integer quantity;

    private BigDecimal price;

    private String imageUrl;

    private String description;


}
