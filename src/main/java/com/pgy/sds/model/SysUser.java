package com.pgy.sds.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 09:40
 * Description:
 */
@Data
@ApiModel(value = "SysUser对象", description = "用户管理")
public class SysUser extends BaseRowModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	@TableId(value = "user_id", type = IdType.NONE)
	@ExcelProperty(value = "userId", index = 0)
	private Integer userId;

	@NotBlank(message = "用户名不能为空")
	@ApiModelProperty(value = "用户名")
	@ExcelProperty(value = "用户名", index = 1)
	private String username;

	@NotBlank(message = "密码不能为空")
	@Length(min = 4, max = 12)
	@ApiModelProperty(value = "密码")
	private String password;

	@NotBlank(message = "邮箱不能为空")
	@Email(message = "邮箱格式不正确")
	@ApiModelProperty(value = "邮箱")
	private String email;

	@JsonIgnore
	@ApiModelProperty(value = "密码盐")
	private String salt;

	@ApiModelProperty(value = "创建者Id")
	private Integer createUserId;

	@ExcelProperty(value = "时间", index = 2)
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "0禁用，1正常")
	private String status;

	@TableField(exist = false)
	private List<SysRole> roleList;
}