package com.pgy.sds.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:   taoyuzhu(taoyuzhu@hulai.com)
 * Date:     2019-07-10 10:40
 * Description:
 */
@Data
@ApiModel(value = "SysMenu对象", description = "菜单管理")
public class SysMenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "menu_id", type = IdType.AUTO)
	private Integer menuId;

	@ApiModelProperty(value = "父菜单ID，一级菜单为0")
	private Integer parentId;

	@ApiModelProperty(value = "菜单名称")
	private String name;

	@ApiModelProperty(value = "菜单URL")
	private String url;

	@ApiModelProperty(value = "授权(多个用逗号分隔，如：user:list,user:create)")
	@TableField(strategy = FieldStrategy.IGNORED)
	private String perms;

	@ApiModelProperty(value = "类型   0：目录   1：菜单   2：按钮")
	private Integer type;

	@ApiModelProperty(value = "菜单图标")
	private String icon;

	@ApiModelProperty(value = "排序")
	private Integer orderNum;

	@TableField(exist = false)
	private List<SysMenu> childSysMenu;

	@TableField(exist = false)
	private Boolean isHave;

}
