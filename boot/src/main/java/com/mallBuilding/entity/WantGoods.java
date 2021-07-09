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
 * @since 2021-07-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class WantGoods implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private Integer userId;

    private Integer goodsId;


}
