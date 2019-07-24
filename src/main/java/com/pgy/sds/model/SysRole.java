package com.pgy.sds.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-24 11:03
 * Description:
 */
@Data
@ApiModel(value="SysRole对象", description="角色管理")
public class SysRole extends Model<SysRole> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	private String roleName;
	private String remark;
	private Long createUserId;
	private String createTime;
	@TableField(exist=false)
	private List<SysMenu> sysMenus;
	@Override
	protected Serializable pkVal() {
		return null;
	}
}
