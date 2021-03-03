package com.github.fashionbrot.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author fashionbrot
 * @version 0.1.0
 * @date 2019/12/8 22:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_menu_role_relation")
public class MenuRoleRelation {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(value = "create_id",fill = FieldFill.INSERT)
    private Long createId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value="create_date",fill = FieldFill.INSERT)
    private Date createDate;

    @TableField("menu_id")
    private Long menuId;

    @TableField("role_id")
    private Long roleId;
}
