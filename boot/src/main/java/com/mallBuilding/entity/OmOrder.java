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
 * @since 2021-07-21
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

    private Boolean hasPayed;

    private Integer hasShipped;

    private Integer hasConfirmed;

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

  public BigDecimal getActualPayAmount() {
    return actualPayAmount;
  }

  public void setActualPayAmount(BigDecimal actualPayAmount) {
    this.actualPayAmount = actualPayAmount;
  }

  public String getBuyerAddress() {
    return buyerAddress;
  }

  public void setBuyerAddress(String buyerAddress) {
    this.buyerAddress = buyerAddress;
  }

  public String getBuyerPhone() {
    return buyerPhone;
  }

  public void setBuyerPhone(String buyerPhone) {
    this.buyerPhone = buyerPhone;
  }

  public String getBuyerName() {
    return buyerName;
  }

  public void setBuyerName(String buyerName) {
    this.buyerName = buyerName;
  }

  public Boolean getHasPayed() {
    return hasPayed;
  }

  public void setHasPayed(Boolean hasPayed) {
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
}
