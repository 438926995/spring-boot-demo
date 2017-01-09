package com.app.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author huwenwen
 * @since 16/12/27
 */
@Data
public class User {
  private String name;
  private BigDecimal value;
  private Date date;
  private Date createTime;
}
