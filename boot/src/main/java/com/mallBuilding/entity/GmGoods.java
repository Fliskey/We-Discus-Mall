package com.mallBuilding.entity;

import java.math.BigDecimal;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2021-07-06
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class GmGoods implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String userId;

    private String name;

    private String type;

    private Integer quantity;

    private BigDecimal price;

    private String imageUrl;

    private String description;


}
