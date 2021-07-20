package com.mallBuilding.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author root
 * @since 2021-07-18
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class OmOrder implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String goodsId;

    private String buyerId;

    private BigDecimal actualPayAmount;

    private String buyerAddress;

    private String buyerPhone;

    private String buyerName;

    private boolean hasPayed;


}
