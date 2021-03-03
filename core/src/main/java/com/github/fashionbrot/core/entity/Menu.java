package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;


@TableName("sys_menu")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Menu extends BaseEntity{

    private static final long serialVersionUID = -8331064931930047236L;

    /**
     * 优先级
     */
    @TableField("priority")
    private Integer priority;
    /**
     * 菜单级别
     * 1 一级菜单
     * 2 二级菜单
     * 3 按钮
     */
    @TableField("menu_level")
    private Integer menuLevel;
    /**
     * 菜单名称
     */
    @TableField("menu_name")
    private String menuName;
    /**
     * 菜单 url
     */
    @TableField("menu_url")
    private String menuUrl;
    /**
     * 父级 菜单id
     */
    @TableField("parent_menu_id")
    private Long parentMenuId;

    /**
     * 权限code
     */
    private String code;

    /**
     * 打开方式（ menuItem页签 menuBlank新窗口）
     */
    private String target;

    /**
     * 是否刷新（0刷新 1不刷新）
     */
    private Integer isRefresh;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 父级 菜单名称
     */
    @TableField(exist = false)
    private  String parentMenuName;

    @TableField(exist = false)
    private  List<Menu> childMenu;

    @TableField(exist = false)
    private  int active;

    @TableField(exist = false)
    private  boolean checked;

    @TableField(exist = false)
    private  boolean open;

    @TableField(exist = false)
    private  String name;
}
