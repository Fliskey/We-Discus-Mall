package com.mallBuilding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author heyun
 * @since 2021-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BuyShow implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String goodsId;

    private String buyerId;

    private String imageUrl;

    private String name;

    private String type;

    private BigDecimal price;

    private Integer quantitySiOrder;

    private String actualPayAmount;

    private Integer hasPayed;

    private Integer hasShipped;

    private Integer hasConfirmed;

}
