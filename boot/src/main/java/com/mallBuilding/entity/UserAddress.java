package com.mallBuilding.entity;

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
 * @since 2021-07-16
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class UserAddress implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer userId;

    private String consigneeName;

    private String consigneeTel;

    private String consigneeAddress;


}
