package com.pgy.sds.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:07
 * Description:
 */
@Data
@ApiModel(value="SysUserToken对象", description="系统用户Token")
public class SysUserToken implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;

	private String token;



}
