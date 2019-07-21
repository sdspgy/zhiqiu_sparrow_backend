package com.pgy.sds.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 09:35
 * Description:
 */
@Data
public class SysLoginForm {

	@NotBlank
	@Length(min = 4, max = 12)
	private String username;
	@NotBlank
	@Length(min = 4, max = 12)
	private String password;
//	private String uuid;
}
