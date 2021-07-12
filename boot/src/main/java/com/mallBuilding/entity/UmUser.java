package com.mallBuilding.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2021-07-03
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class UmUser implements Serializable {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String name;

    private String telNumber;

    private String email;


}
