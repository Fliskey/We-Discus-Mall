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
    public class UmLogin implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String password;

    private String salt;


}
