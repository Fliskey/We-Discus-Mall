package com.mallBuilding.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Fliskey
 * @since 2021-07-07
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String name;

    private String telNumber;

    private String password;


}
