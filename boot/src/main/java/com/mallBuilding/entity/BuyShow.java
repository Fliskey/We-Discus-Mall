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

    private String sellerId;

    private String imageUrl;

    private String name;
    private String buyerName;

    private String type;

    private BigDecimal price;

    private Integer quantitySiOrder;

    private String buyerPhone;

    private String buyerAddress;
    private String actualPayAmount;

    private Integer hasPayed;

    private Integer hasShipped;

    private Integer hasConfirmed;

    private String shippedId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantitySiOrder() {
        return quantitySiOrder;
    }

    public void setQuantitySiOrder(Integer quantitySiOrder) {
        this.quantitySiOrder = quantitySiOrder;
    }

    public String getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(String actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public Integer getHasPayed() {
        return hasPayed;
    }

    public void setHasPayed(Integer hasPayed) {
        this.hasPayed = hasPayed;
    }

    public Integer getHasShipped() {
        return hasShipped;
    }

    public void setHasShipped(Integer hasShipped) {
        this.hasShipped = hasShipped;
    }

    public Integer getHasConfirmed() {
        return hasConfirmed;
    }

    public void setHasConfirmed(Integer hasConfirmed) {
        this.hasConfirmed = hasConfirmed;
    }

    public String getShippedId() {
        return shippedId;
    }

    public void setShippedId(String shippedId) {
        this.shippedId = shippedId;
    }
}
