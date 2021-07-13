package com.mallBuilding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author Fliskey
 * @since 2021-07-13
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class PurchaseGoods {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer userId;

    private Integer goodsId;

    private Integer quantity;


}
