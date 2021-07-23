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
public class GoodsAndBuyer implements Serializable {

    private static final long serialVersionUID=1L;

    private String imageUrl;

    private String name;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private BigDecimal actualPayAmount;








}
