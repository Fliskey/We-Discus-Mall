package com.mallBuilding.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author root
 * @since 2021-07-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ShippedGoods implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer orderId;

    private Integer shippedId;


}
